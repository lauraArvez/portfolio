using FontAwesome.Sharp;
using InterfazRest.Services;
using InterfazRest.Utilities;
using System;
using System.Drawing;
using System.Runtime.InteropServices;
using System.Windows.Forms;

namespace InterfazRest
{
    /// <summary>
    /// Formulario principal para la administración, que permite la navegación a diferentes secciones de la aplicación.
    /// </summary>
    public partial class HomeAdminForm : Form
    {
        // Botón actualmente activo
        private IconButton currentBtn;

        // Botón previamente activo (para restaurar su estado cuando se activa un nuevo botón)
        private IconButton previousBtn;

        // Panel para mostrar el borde izquierdo del botón activo
        private Panel leftBorderBtn;

        // Formulario hijo actualmente abierto
        private Form currentChildForm;

        // Servicio de autenticación inyectado
        private readonly IAuthService _authService;

        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="HomeAdminForm"/> con el nombre de usuario especificado.
        /// </summary>
        /// <param name="username">Nombre de usuario que ha iniciado sesión.</param>
        public HomeAdminForm(string username, IAuthService authService)
        {
            InitializeComponent();

            // Configura el servicio de autenticación
            this._authService = authService;

            // Asigna el nombre de usuario al label correspondiente
            lblUsername.Text = username;

            // Inicialización del panel de borde izquierdo para botones activos
            leftBorderBtn = new Panel { Size = new Size(7, 60) };
            panelMenu.Controls.Add(leftBorderBtn);
        }

        /// <summary>
        /// Activa un botón del menú, aplicando estilos visuales y mostrando el formulario hijo correspondiente.
        /// </summary>
        /// <param name="senderBtn">Botón que se activará.</param>
        /// <param name="childForm">Formulario hijo que se abrirá.</param>
        private void ActivateButtonAndOpenForm(IconButton senderBtn, Form childForm)
        {
            previousBtn = currentBtn; // Guarda el botón activo actual como el anterior
            currentBtn = senderBtn;   // Actualiza el botón activo actual

            if (currentBtn != null)
            {
                FormUtilities.DisableButton(previousBtn, ThemeColor.Club.Color1); // Desactiva el botón anterior
                FormUtilities.ActivateButton(
                    currentBtn,
                    leftBorderBtn,
                    iconCurrentChildForm,
                    lblName,                    
                    ThemeColor.Club.Color1, 
                    ThemeColor.Club.Color4); 
                FormUtilities.OpenChildForm(ref currentChildForm, childForm, panelDesktop, lblName); // Abre el formulario hijo
            }
        }

        /// <summary>
        /// Propiedad pública para acceder al formulario hijo actualmente abierto.
        /// </summary>
        public Form CurrentChildForm
        {
            get { return currentChildForm; }
            set { currentChildForm = value; }
        }

        /// <summary>
        /// Propiedad pública para acceder al panel donde se colocan los formularios hijos.
        /// </summary>
        public Panel PanelDesktop
        {
            get { return panelDesktop; }
        }

        /// <summary>
        /// Propiedad pública para acceder a la etiqueta del título del formulario hijo actual.
        /// </summary>
        public Label LblName
        {
            get { return lblName; }
        }

        /// <summary>
        /// Actualiza el título mostrado en la barra de título del formulario.
        /// </summary>
        /// <param name="title">Nuevo título a mostrar.</param>
        public void UpdateTitle(string title)
        {
            lblName.Text = title;
        }

        // Eventos de clic de los botones del menú para abrir diferentes formularios
        private void iconButtonAtleta_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new Atletas(this, _authService));
        }

        private void iconButtonRegister_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new RegisterForm(_authService));
        }

        private void iconButtonEvent_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new EventForm(_authService));
        }

        private void iconButtonTrainers_Click(object sender, EventArgs e)
        {
           ActivateButtonAndOpenForm((IconButton)sender, new TrainerForm(_authService));
        }

        private void iconButtonCalendar_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new CalendarForm());
        }

        private void iconButtonContact_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new ContactForm());
        }

        private void iconButtonAdmin_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new AdministrationForm());
        }

        private void iconButtonShop_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new ProfileForm(_authService));
        }

        private void iconButtonProfile_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new ProfileForm(_authService));
        }

        /// <summary>
        /// Cierra la aplicación cuando se hace clic en el botón de cerrar y abre el FormHome.
        /// </summary>
        private void iconButtonLogout_Click(object sender, EventArgs e)
        {
            this.Close(); // Cierra el formulario actual

            // abre el nuevo formulario (FormHome)
            FormHome formHome = new FormHome(_authService);
            formHome.Show();
        }        

        /// <summary>
        /// Cierra la aplicación cuando se hace clic en el botón de cierre.
        /// </summary>
        private void btnExit_Click(object sender, EventArgs e)
        {
            FormActions.ExitApplication();
        }

        /// <summary>
        /// Alterna el estado de maximizar/restaurar de la ventana.
        /// </summary>
        private void btnMax_Click(object sender, EventArgs e)
        {
            FormActions.ToggleMaximize(this);
        }

        /// <summary>
        /// Minimiza la ventana cuando se hace clic en el botón de minimizar.
        /// </summary>
        private void btnMinus_Click(object sender, EventArgs e)
        {
            FormActions.Minimize(this);
        }               

        /// <summary>
        /// Evento para arrastrar el formulario desde la barra de título.
        /// </summary>
        private void panelTitleBar_MouseDown(object sender, MouseEventArgs e)
        {
            FormActions.HandleTitleBarDrag(this);
        }

        /// <summary>
        /// Restablece el estado del formulario al hacer clic en el logotipo de Home.
        /// </summary>
        private void pictureBox1_Click(object sender, EventArgs e)
        {
            if (currentChildForm != null)
            {
                currentChildForm.Close();
            }
            FormUtilities.Reset(
                iconCurrentChildForm,
                leftBorderBtn,
                lblName,
                "Home"
            );                 
        }
    }
}
