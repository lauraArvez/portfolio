using FontAwesome.Sharp;
using InterfazRest.Services;
using InterfazRest.Utilities;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InterfazRest
{
    /// <summary>
    /// Formulario para registrar nuevos usuarios.
    /// </summary>
    public partial class RegisterForm : Form
    {
        // HttpClient estático para reutilización y evitar el agotamiento de recursos
        private static readonly HttpClient client = new HttpClient();

        // Servicio de autenticación para manejar el token y las solicitudes HTTP
        private readonly IAuthService _authService;

        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="RegisterForm"/>.
        /// </summary>
        /// <param name="authService">Instancia del servicio de autenticación.</param>
        public RegisterForm(IAuthService authService)
        {
            InitializeComponent();
            _authService = authService;
        }


        /// <summary>
        /// Evento Click para el botón de Registrar. Valida los campos y envía una solicitud de registro a la API.
        /// </summary>
        private async void btnSubmit_Click(object sender, EventArgs e)
        {
            // Obtener valores de los TextBox
            string username = txtUsername.Text;
            string email = txtEmail.Text;
            string password = txtPassword.Text;
            List<string> roles = new List<string> { "User" };

            // Validar que los campos no estén vacíos
            if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(password))
            {
                MessageBox.Show("Todos los campos son obligatorios.");
                return;
            }

            // Crear objeto de solicitud
            RegistrarRequest request = new RegistrarRequest
            {
                Username = username,
                Password = password,
                Email = email,
                Roles = roles
            };

            // Hacer la solicitud POST
            var response = await RegistrarUsuarioAsync(request);

            // Manejar la respuesta
            if (response.StatusCode == System.Net.HttpStatusCode.OK)
            {
                MessageBox.Show(response.Message);
                // Limpiar los campos después del registro
                LoginUtilities.ClearInputFields(txtUsername, txtPassword);
                txtEmail.Clear();
            }
            else
            {
                MessageBox.Show(response.Message);
            }
        }

        /// <summary>
        /// Envía una solicitud POST a la API para registrar un usuario.
        /// </summary>
        /// <param name="request">Objeto de solicitud con los datos del usuario.</param>
        /// <returns>Objeto ApiResponse con el resultado de la solicitud.</returns>
        private async Task<ApiResponse> RegistrarUsuarioAsync(RegistrarRequest request)
        {
            try
            {
                // URL de la API
                string url = "http://localhost:8085/api/usuario/registrar";

                // Serializar la solicitud a JSON
                var json = JsonConvert.SerializeObject(request);

                var data = new StringContent(json, Encoding.UTF8, "application/json");

                // Añadir el token de autenticación al encabezado de la solicitud
                client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", _authService.GetToken());

                // Hacer la solicitud POST
                var response = await client.PostAsync(url, data);
                var responseString = await response.Content.ReadAsStringAsync();

                return new ApiResponse
                {
                    Message = responseString,
                    StatusCode = response.StatusCode
                };
            }
            catch (HttpRequestException httpRequestException)
            {
                return new ApiResponse
                {
                    Message = $"Error al enviar la solicitud: {httpRequestException.Message}",
                    StatusCode = System.Net.HttpStatusCode.InternalServerError
                };
            }
            catch (Exception ex)
            {
                return new ApiResponse
                {
                    Message = $"Error inesperado: {ex.Message}",
                    StatusCode = System.Net.HttpStatusCode.InternalServerError
                };
            }
        }

        /// <summary>
        /// Cierra el formulario actual.
        /// </summary>
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        // Evento MouseDown para mostrar la contraseña
        private void iconButtonPass_MouseDown(object sender, MouseEventArgs e)
        {
            txtPassword.UseSystemPasswordChar = false;
        }

        // Evento MouseUp para ocultar la contraseña
        private void iconButtonPass_MouseUp(object sender, MouseEventArgs e)
        {
            txtPassword.UseSystemPasswordChar = true;
        }

    }
}

