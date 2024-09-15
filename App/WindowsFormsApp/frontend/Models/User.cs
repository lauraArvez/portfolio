using Newtonsoft.Json; // serializar y deserializar objetos a y desde JSON. incluye JsonConvert, que facilita convertir objetos C# a JSON y viceversa
using System.Collections.Generic; 

namespace InterfazRest
{
    /// <summary>
    /// Clase que representa la solicitud para registrar un usuario.
    /// </summary>
    public class RegistrarRequest
    {
        /// <summary>
        /// Nombre de usuario.
        /// </summary>
        [JsonProperty("username")]
        public string Username { get; set; }

        /// <summary>
        /// Contraseña del usuario.
        /// </summary>
        [JsonProperty("password")]
        public string Password { get; set; }

        /// <summary>
        /// Email del usuario.
        /// </summary>
        [JsonProperty("email")]
        public string Email { get; set; }

        /// <summary>
        /// Lista de roles asignados al usuario.
        /// </summary>
        [JsonProperty("roles")]
        public List<string> Roles { get; set; }
    }

    /// <summary>
    /// Clase que representa la respuesta de la API.
    /// </summary>
    public class ApiResponse
    {
        /// <summary>
        /// Mensaje de respuesta de la API.
        /// </summary>
        public string Message { get; set; }

        /// <summary>
        /// Código de estado HTTP de la respuesta.
        /// </summary>
        public System.Net.HttpStatusCode StatusCode { get; set; }
    }
}
