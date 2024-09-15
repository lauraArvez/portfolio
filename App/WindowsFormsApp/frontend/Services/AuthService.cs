using Newtonsoft.Json;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using Serilog;

namespace InterfazRest.Services
{
    /// <summary>
    /// Servicio de autenticación que maneja el proceso de login y gestión de tokens de autenticación.
    /// </summary>
    public class AuthService : IAuthService
    {
        // Cliente HTTP para realizar solicitudes al servidor       
        private readonly HttpClient _client;

        // Token de autenticación almacenado
        private string _token;

        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="AuthService"/> con un cliente HTTP proporcionado.
        /// </summary>
        /// <param name="client">Cliente HTTP para realizar solicitudes al servidor.</param>
        public AuthService(HttpClient client)
        {
            _client = client;
        }

        /// <summary>
        /// Realiza una solicitud de autenticación al servidor utilizando las credenciales proporcionadas.
        /// </summary>
        /// <param name="username">Nombre de usuario.</param>
        /// <param name="password">Contraseña del usuario.</param>
        /// <returns>Un <see cref="LoginResult"/> que indica si la autenticación fue exitosa, e incluye el token y roles si lo fue.</returns>
        public async Task<LoginResult> LoginAsync(string username, string password)
        {
            Log.Information("Intentando autenticar al usuario {Username}", username);
            var loginRequest = new 
            {
                username = username,
                password = password
            };

            var json = JsonConvert.SerializeObject(loginRequest);
            var data = new StringContent(json, Encoding.UTF8, "application/json");

            try
            {
                // Realiza una solicitud POST al servidor para autenticar al usuario.
                var response = await _client.PostAsync("http://localhost:8085/api/auth/login", data);

                if (response.IsSuccessStatusCode)
                {
                    // Si la respuesta es exitosa, deserializa el cuerpo de la respuesta para obtener el token.
                    var responseBody = await response.Content.ReadAsStringAsync();
                    var loginResponse = JsonConvert.DeserializeObject<LoginResponse>(responseBody);

                    // Almacena el token de autenticación recibido.
                    _token = loginResponse.Token;

                    // Decodifica los roles del usuario a partir del token.
                    var roles = DecodeJwtRoles(_token);

                    // Retorna el resultado de la autenticación, incluyendo el token y los roles.
                    Log.Information("Usuario {Username} autenticado con éxito", username);
                    return new LoginResult
                    {
                        IsAuthenticated = true,
                        Token = _token,
                        Roles = roles
                    };
                }

                // Si la autenticación falla, retorna un resultado indicando que no fue autenticado.
                Log.Warning("Fallo en la autenticación del usuario {Username}", username);
                return new LoginResult { IsAuthenticated = false };

            }
            catch (Exception ex)
            {
                // Maneja excepciones que puedan ocurrir durante la solicitud de autenticación.
                Log.Error(ex, "Error al autenticar al usuario {Username}", username);
                throw new Exception("Error al conectarse al servidor de autenticación.", ex);
            }
        }

        /// <summary>
        /// Establece el token de autenticación en el servicio y opcionalmente configura
        /// el encabezado de autorización para el cliente HTTP utilizado en las solicitudes futuras.
        /// </summary>
        /// <param name="token">El token de autenticación que se utilizará para futuras solicitudes.</param>
        public void SetToken(string token)
        {
            _token = token;            
            _client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", _token);
            Log.Information("Token de autenticación establecido.");
        }


        /// <summary>
        /// Obtiene el token de autenticación actual.
        /// </summary>
        /// <returns>El token de autenticación.</returns>
        public string GetToken()
        {
            return _token;
        }


        /// <summary>
        /// Verifica si el usuario está autenticado verificando la existencia del token.
        /// </summary>
        /// <returns>True si el usuario está autenticado, false en caso contrario.</returns>
        public bool IsAuthenticated()
        {
            return !string.IsNullOrEmpty(_token);
        }


        /// <summary>
        /// Decodifica los roles de usuario a partir del token JWT.
        /// </summary>
        /// <param name="token">Token JWT.</param>
        /// <returns>Una matriz de roles decodificados.</returns>
         private string[] DecodeJwtRoles(string token)
        {
            var handler = new JwtSecurityTokenHandler();

            // Valida que el token JWT sea legible
            if (handler.CanReadToken(token))
            {
                var jwtToken = handler.ReadJwtToken(token);

                // Intenta extraer el valor del claim que contiene los roles.
                var rolesClaim = jwtToken.Claims.FirstOrDefault(c => c.Type == "CLAIM_TOKEN");

                if (rolesClaim != null)
                {
                    // Divide el contenido del claim en roles separados por comas
                    return rolesClaim.Value.Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
                }
            }

            // Si no se puede leer el token o no se encuentra el claim, retorna un array vacío
            return new string[] { };
         }
               
        /// <summary>
        /// Clase privada para manejar la respuesta del servidor al realizar login.
        /// </summary>
        private class LoginResponse
        {
            public string Token { get; set; }
        }
    }

    /// <summary>
    /// Clase que representa el resultado del proceso de autenticación.
    /// </summary>
    public class LoginResult
    {
        // Indica si la autenticación fue exitosa
        public bool IsAuthenticated { get; set; }

        // Token de autenticación
        public string Token { get; set; }

        // Roles decodificados del token
        public string[] Roles { get; set; } 
    }

}