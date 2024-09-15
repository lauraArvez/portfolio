using InterfazRest.Services;
using InterfazRest.Utilities;
using Newtonsoft.Json;
using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Windows.Forms;
using Serilog;

namespace InterfazRest
{

    /// <summary>
    /// Formulario para el restablecimiento de la contraseña de un usuario administrador.
    /// </summary>
    public partial class ResetPasswordForm : Form
    {
        // HttpClient para realizar solicitudes HTTP
        private static readonly HttpClient client = new HttpClient();

        // Servicio de autenticación inyectado
        private readonly IAuthService _authService;

        // Referencia al formulario principal de administración
        private HomeAdminForm homeAdminForm;        

        // Token de autenticación para las solicitudes
        //private string token;

        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="ResetPasswordForm"/> y recibe una referencia al formulario principal.
        /// </summary>
        /// <param name="homeAdminForm">Referencia al formulario principal de administración.</param>

        public ResetPasswordForm(HomeAdminForm homeAdminForm, IAuthService authService)
        {
            InitializeComponent();
            _authService = authService;
            this.homeAdminForm = homeAdminForm;
            
            // El encabezado de autenticación debe estar configurado en el HttpClient si el token ya se ha establecido
            var token = _authService.GetToken(); // Este token debe ser no nulo y válido
            if (string.IsNullOrEmpty(token))
            {
                Log.Warning("Token de autenticación no válido o no presente.");
                MessageBox.Show("Error: El token de autenticación no válido.");
            }
            else
            {
                // Configura el encabezado de autorización para el HttpClient
                client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
                Log.Information("Formulario de restablecimiento de contraseña inicializado correctamente.");
            }

            // Asocia el evento KeyDown para aceptar la tecla Enter en el txtPass
            txtNewPassword.KeyDown += TxtPass_KeyDown;
        }

        /// <summary>
        /// Maneja el evento KeyDown para permitir el envío de credenciales al presionar Enter.
        /// </summary>
        private void TxtPass_KeyDown(object sender, KeyEventArgs e)
        {
            // Verificar si la tecla presionada es Enter
            if (e.KeyCode == Keys.Enter)
            {
                // Simula un clic en el botón de inicio de sesión
                btnResetPassword.PerformClick();
            }
        }
    

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en el botón de salida.
        /// Regresa al formulario principal de administración.
        /// </summary>
        private void btnExit_Click(object sender, EventArgs e)
        {
            string username = "admin";
            HomeAdminForm formHome = new HomeAdminForm(username,_authService);
            formHome.Show();
            this.Hide();
        }

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en el botón para restablecer la contraseña.
        /// Envía una solicitud HTTP para actualizar la contraseña del usuario.
        /// </summary>
        private async void btnResetPassword_Click(object sender, EventArgs e)
        {
            var username = txtUsername.Text;
            var newPassword = txtNewPassword.Text;

            if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(newPassword))
            {
                MessageBox.Show("Por favor, ingrese su nombre de usuario y la nueva contraseña.");
                return;
            }

            var resetPasswordRequest = new
            {
                username = username,
                password = newPassword
            };

            var json = JsonConvert.SerializeObject(resetPasswordRequest);
            Console.WriteLine($"Datos enviados: {json}");
            var data = new StringContent(json, Encoding.UTF8, "application/json");

            var url = "http://localhost:8085/api/usuario/reset-password";

            try
            {
                // Verifica los encabezados antes de enviar la solicitud
                //MessageBox.Show("Encabezados enviados: " + _authService.GetToken());

                Log.Information("Enviando solicitud de restablecimiento de contraseña para {Username}", username);
                var response = await client.PutAsync(url, data);

                if (response.IsSuccessStatusCode)
                {
                    MessageBox.Show("Contraseña actualizada con éxito.");
                    Log.Information("Contraseña actualizada con éxito para {Username}", username);
                    // Cerrar el formulario después de actualizar la contraseña
                    this.Close(); 
                }
                else
                {
                    var responseContent = await response.Content.ReadAsStringAsync();
                    Log.Warning("Error al actualizar la contraseña para {Username}: {ResponseContent}", username, responseContent);
                    MessageBox.Show($"No se pudo actualizar la contraseña.");// Respuesta del servidor: {responseContent}");
                }
            }
            
            catch (Exception ex)
            {
                Log.Error(ex, "Error al intentar restablecer la contraseña para {Username}", username);
                MessageBox.Show($"Error al realizar la solicitud de recuperación de contraseña: {ex.Message}");
            }
        }

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en el botón "Volver".
        /// Muestra un mensaje de confirmación antes de salir del formulario.
        /// </summary>
        private void iconButtonVolver_Click(object sender, EventArgs e)
        {
            var result = MessageBox.Show(
                "Salir sin restablecer la contraseña. ¿Desea continuar?",
                "Salir",
                MessageBoxButtons.YesNo,
                MessageBoxIcon.Warning
            );

            if (result == DialogResult.Yes)
            {
                var atletasForm = new Atletas(homeAdminForm, _authService);

                // Almacena el formulario hijo actual en una variable intermedia
                var currentChildForm = homeAdminForm.CurrentChildForm;

                FormUtilities.OpenChildForm(ref currentChildForm, atletasForm, homeAdminForm.PanelDesktop, homeAdminForm.LblName);

                // Actualiza la propiedad CurrentChildForm del homeAdminForm
                homeAdminForm.CurrentChildForm = currentChildForm;

                this.Close();
            }
        }
    }
}


