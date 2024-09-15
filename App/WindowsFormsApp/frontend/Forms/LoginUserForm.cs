using InterfazRest.Services;
using InterfazRest.Utilities;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
namespace InterfazRest
{
    /// <summary>
    /// Formulario de inicio de sesión para usuarios. Gestiona el proceso de autenticación.
    /// </summary>
    public partial class LoginUserForm : Form
    {
        // Servicio de autenticación
        private readonly IAuthService _authService;

        /// <summary>
        /// Constructor de LoginUserForm. Inicializa los componentes y configura el servicio de autenticación.
        /// </summary>
        /// <param name="authService">Instancia del servicio de autenticación.</param>
        public LoginUserForm(IAuthService authService)
        {
            InitializeComponent();
            _authService = authService;

            // Asocia el evento KeyDown para aceptar la tecla Enter en el txtPass
            txtPass.KeyDown += TxtPass_KeyDown;
        }

        /// <summary>
        /// Maneja el evento KeyDown para mostrar un ToolTip si se presiona Enter en el campo de contraseña.
        /// </summary>
        private void TxtPass_KeyDown(object sender, KeyEventArgs e)
        {
            LoginUtilities.ShowTooltipOnEnter(e, toolTip, txtPass, "Por favor, presione el botón 'Entrar' para continuar.");
        }

        /// <summary>
        /// Maneja el evento de clic en el botón de volver para regresar a la pantalla principal.
        /// </summary>
        private void iconButtonVolver_Click(object sender, EventArgs e)
        {
            // Crea una nueva instancia de FormHome y lo muestra
            FormHome formHome = new FormHome(_authService);
            formHome.Show();
            this.Hide();
        }

        /// <summary>
        /// Maneja el evento de clic en el link de recuperación de contraseña.
        /// Muestra un mensaje informando al usuario que debe contactar a su entrenador.
        /// </summary>
        private void linkLabelRecuperar_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            // Mostrar el mensaje personalizado en un MessageBox
            MessageBox.Show("Póngase en contacto con su entrenador para restablecer su contraseña.",
                            "Recuperar Contraseña",
                            MessageBoxButtons.OK,
                            MessageBoxIcon.Information);
        }

        /// <summary>
        /// Maneja el evento de clic en el botón de login para iniciar el proceso de autenticación.
        /// </summary>
        private async void btnLogin_Click(object sender, EventArgs e)
        {
            // Obtener datos ingresados por el usuario en el formulario.
            var username = txtUsername.Text;
            var password = txtPass.Text;

            try
            {
                // Utilizar IAuthService para autenticar al usuario
                var loginResult = await _authService.LoginAsync(username, password);

                // Verificar si la autenticación fue exitosa y el usuario tiene el rol adecuado
                if (loginResult.IsAuthenticated && loginResult.Roles.Contains("ROLE_USER"))
                {
                    // Restablecer el contador de intentos fallidos
                    LoginUtilities.ResetLoginAttempts();

                    // Si es exitoso, abrir el formulario de la interfaz del atleta
                    FormHomeAtleta homeAtleta = new FormHomeAtleta(username, _authService);
                    homeAtleta.Show();
                    this.Hide();
                }
                else
                {
                    // Incrementar intentos de inicio de sesión fallidos
                    LoginUtilities.IncrementLoginAttempts();

                    if (LoginUtilities.HasExceededMaxAttempts())
                    {
                        // Si se han alcanzado los intentos máximos, muestra un mensaje y regresa al formulario principal.
                        MessageBox.Show("Se ha superado el número máximo de intentos");
                        FormHome formHome = new FormHome(_authService);
                        formHome.Show();
                        this.Hide();
                    }
                    else
                    {
                        // Mostrar un mensaje de error y limpiar los campos si la autenticación falla
                        MessageBox.Show("Credenciales incorrectas o no está registrado.");
                        
                        LoginUtilities.ClearInputFields(txtUsername, txtPass);
                    }
                }
            }
            catch (Exception ex)
            {
                // Mostrar un mensaje de error en caso de una excepción inesperada
                MessageBox.Show($"Ocurrió un error: {ex.Message}");
                
                LoginUtilities.ClearInputFields(txtUsername, txtPass);
            }
        }
    }
}

