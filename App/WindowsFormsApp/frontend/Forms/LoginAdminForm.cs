using InterfazRest.Services;
using InterfazRest.Utilities;
using System;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace InterfazRest
{
    /// <summary>
    /// Formulario de inicio de sesión para administradores.
    /// </summary>
    public partial class LoginAdminForm : Form
    {
        // Servicio de autenticación inyectado
        private readonly IAuthService _authService;


        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="LoginAdminForm"/>.
        /// </summary>
        /// <param name="authService">Servicio de autenticación proporcionado para gestionar el inicio de sesión.</param>
        public LoginAdminForm(IAuthService authService)
        {
            InitializeComponent();
            _authService = authService;

            // Asociar el evento KeyDown al control txtPass
            txtPass.KeyDown += TxtPass_KeyDown;
        }

        /// <summary>
        /// Maneja el evento KeyDown para mostrar un ToolTip si se presiona Enter en el campo de contraseña.
        /// Reutiliza el método de LoginUtilities para hacerlo más eficiente.
        /// </summary>
        private void TxtPass_KeyDown(object sender, KeyEventArgs e)
        {
            LoginUtilities.ShowTooltipOnEnter(e, toolTip, txtPass, "Por favor, presione el botón 'Entrar' para continuar.");
        }

        //// <summary>
        /// Evento que se ejecuta cuando el formulario se carga.
        /// Configura el tamaño inicial del formulario y centra el panel de login.
        /// </summary>
        private void LoginAdminForm_Load(object sender, EventArgs e)
        {
            this.Size = new Size(800, 600); 
            CenterPanel();
        }
        /// <summary>
        /// Evento que se ejecuta cuando el formulario cambia de tamaño.
        /// Centra el panel de login en el formulario.
        /// </summary>
        private void LoginAdminForm_Resize(object sender, EventArgs e)
        {
            CenterPanel();
        }

        /// <summary>
        /// Centra el panel de login en el formulario.
        /// </summary>
        private void CenterPanel()
        {
            // Calcular el centro del formulario y ajustar la ubicación del panel
            panelLogin.Left = (this.ClientSize.Width - panelLogin.Width) / 2;
            panelLogin.Top = (this.ClientSize.Height - panelLogin.Height) / 2;
        }

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en el botón de volver.
        /// Regresa al formulario principal de inicio.
        /// </summary>
        private void iconButtonVolver_Click(object sender, EventArgs e)
        {
            FormHome formHome = new FormHome(_authService);
            formHome.Show();
            this.Hide();
        }

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en el botón de salir.
        /// Cierra la aplicación.
        /// </summary>
        private void btnExit_Click(object sender, EventArgs e)
        {
            FormActions.ExitApplication();
        }

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en el botón de login.
        /// Realiza el proceso de autenticación y maneja el acceso al sistema.
        /// </summary>
        private async void btnLogin_Click(object sender, EventArgs e)
        {
            // Obtiene los datos ingresados por el usuario.
            var username = txtUsername.Text;
            var password = txtPass.Text;

            // Capturar y manejar cualquier error que ocurra durante la autenticación
            try
            {
                // Llama al método Login del servicio de autenticación.
                var loginResult = await _authService.LoginAsync(username, password);

                if (loginResult.IsAuthenticated && loginResult.Roles.Contains("ROLE_ADMIN"))
                {
                    LoginUtilities.ResetLoginAttempts(); // Restablecer intentos fallidos

                    // Si la autenticación es exitosa, muestra el formulario principal de administración.
                    HomeAdminForm homeAdminForm = new HomeAdminForm(username, _authService);
                    homeAdminForm.Show();
                    this.Hide();
                }
                else
                {
                    LoginUtilities.IncrementLoginAttempts(); // Incrementa el contador de intentos fallidos
                    if (LoginUtilities.HasExceededMaxAttempts())   //if (loginAttempts >= maxAttempts)
                    {
                        // Si se han alcanzado los intentos máximos, muestra un mensaje y regresa al formulario principal.
                        MessageBox.Show("Se ha superado el número máximo de intentos");

                        FormHome formHome = new FormHome(_authService);
                        formHome.Show();
                        this.Hide();
                    }
                    else
                    {
                        // Muestra un mensaje de error si las credenciales son incorrectas.
                        MessageBox.Show("Credenciales incorrectas o no tienes permisos de administrador.");

                        // Limpia los campos de entrada en caso de error.
                        LoginUtilities.ClearInputFields(txtUsername, txtPass);
                    }
                }
            }
            catch (Exception ex)
            {
                // Muestra un mensaje de error si ocurre un fallo en la autenticación.
                MessageBox.Show($"Ocurrió un error: {ex.Message}");
                
                // Limpia los campos de entrada en caso de error.
                LoginUtilities.ClearInputFields(txtUsername, txtPass);
            }
        }
       
    }
}