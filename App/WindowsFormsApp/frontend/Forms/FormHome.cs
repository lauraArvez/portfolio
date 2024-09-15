using FontAwesome.Sharp;
using InterfazRest.Services;
using InterfazRest.Utilities;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace InterfazRest
{
    /// <summary>
    /// Formulario principal de la aplicación, utilizado para la navegación entre diferentes secciones.
    /// </summary>
    public partial class FormHome : Form
    {
        // Botón actualmente activo
        private IconButton currentBtn;

        // Panel que representa el borde izquierdo del botón activo
        private Panel leftBorderBtn;

        // Formulario hijo actualmente abierto en el panel principal
        private Form currentChildForm;

        // Botón previamente activo
        private IconButton previousBtn;

        // Servicio de autenticación
        private readonly IAuthService _authService;

        /// <summary>
        /// Constructor de FormHome. Inicializa los componentes y configura el panel de borde izquierdo.
        /// </summary>
        /// <param name="authService">Servicio de autenticación inyectado.</param>
        public FormHome(IAuthService authService)
        {
            InitializeComponent();

            // Asigna el servicio de autenticación
            _authService = authService;

            // Inicializa y configura el panel que representa el borde izquierdo del botón activo
            leftBorderBtn = new Panel();
            leftBorderBtn.Size = new Size(7, 60);            
            panelMenu.Controls.Add(leftBorderBtn);
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en el botón de Club. 
        /// Abre el formulario de inicio de sesión para administradores.
        /// </summary>
        private void iconButtonClub_Click(object sender, EventArgs e)
        {
            OpenFormAndCloseCurrent(new LoginAdminForm(_authService));
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en el botón de Atletas.
        /// Abre el formulario de inicio de sesión para usuarios.
        /// </summary>
        private void iconButtonAtleta_Click(object sender, EventArgs e)
        {
            OpenFormAndCloseCurrent(new LoginUserForm(_authService));
        }

        /// <summary>
        /// Abre un nuevo formulario y cierra el formulario actual.
        /// </summary>
        /// <param name="form">Formulario a abrir.</param>
        private void OpenFormAndCloseCurrent(Form form)
        {
            // Mostrar el nuevo formulario
            form.Show();

            // Cerrar el formulario actual
            this.Hide();

            // Cerrar el formulario principal cuando el nuevo formulario se cierra
            form.FormClosed += (s, args) => this.Close();
        }

        /// <summary>
        /// Maneja el clic en un botón del menú, activando el botón y abriendo el formulario correspondiente.
        /// </summary>
        /// <param name="button">El botón que se ha clicado.</param>
        /// <param name="childForm">El formulario hijo que se abrirá.</param>
        private void HandleButtonClick(IconButton button, Form childForm)
        {
            // Guarda el botón activo actual como el anterior
            previousBtn = currentBtn;
            // Actualiza el botón activo actual
            currentBtn = button;

            if (currentBtn != null)
            {
                // Desactiva el botón anterior y activa el actual
                FormUtilities.DisableButton(previousBtn, ThemeColor.Club.Color1 );
                FormUtilities.ActivateButton(
                    currentBtn, leftBorderBtn, iconCurrentChildForm,
                    lblTitleChildForm,
                    ThemeColor.Club.Color1,  
                    ThemeColor.Club.Color5);

                // Abre el nuevo formulario hijo
                FormUtilities.OpenChildForm(ref currentChildForm, childForm, panelDesktop, lblTitleChildForm);
            }
        }

        /// <summary>
        /// Evento que se ejecuta al cerrar cualquier formulario hijo. 
        /// Cierra el formulario principal.
        /// </summary>
        private void ChildForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en el botón de cerrar sesión.
        /// Cierra la aplicación.
        /// </summary>
        private void iconButtonLogout_Click(object sender, EventArgs e)
        {
            FormActions.ExitApplication();
        }
                
        /// <summary>
        /// Evento que se ejecuta al mantener presionado el ratón en la barra de título.
        /// Permite mover la ventana del formulario.
        /// </summary>
        private void panelTitleBar_MouseDown(object sender, MouseEventArgs e)
        {
            FormActions.HandleTitleBarDrag(this);
        }

        /// <summary>
        /// Evento que se ejecuta al cerrar el formulario LoginUserForm. 
        /// Cierra el formulario principal.
        /// </summary>
        private void LoginUserForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// Evento que se ejecuta al cerrar el formulario LoginAdminForm.
        /// Cierra el formulario principal.
        /// </summary>
        private void LoginAdminForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en el botón de cerrar la aplicación.
        /// </summary>
        private void btnExit_Click(object sender, EventArgs e)
        {
            FormActions.ExitApplication();
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en el botón de maximizar/restaurar la ventana.
        /// </summary>
        private void btnMax_Click(object sender, EventArgs e)
        {
            FormActions.ToggleMaximize(this);
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en el botón de minimizar la ventana.
        /// </summary>
        private void btnMinus_Click(object sender, EventArgs e)
        {
            FormActions.Minimize(this);
        }
    }
}
