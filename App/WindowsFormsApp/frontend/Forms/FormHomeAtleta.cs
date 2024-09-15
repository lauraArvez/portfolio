using FontAwesome.Sharp;
using InterfazRest.Services;
using InterfazRest.Utilities;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace InterfazRest
{
    /// <summary>
    /// Formulario principal para la interfaz del atleta. Gestiona la navegación y visualización de formularios secundarios.
    /// </summary>
    public partial class FormHomeAtleta : Form
    {
        // Nombre de usuario actual
        private string username;

        // Botón actualmente activo en el menú
        private IconButton currentBtn;

        // Botón previamente activo
        private IconButton previousBtn;

        // Panel para mostrar un borde izquierdo en el botón activo
        private Panel leftBorderBtn;

        // Formulario hijo actualmente mostrado en el panel principal
        private Form currentChildForm;

        // Servicio de autenticación
        private readonly IAuthService _authService;
       
        /// <summary>
        /// Constructor del formulario principal del atleta. Inicializa los componentes y configura el entorno inicial.
        /// </summary>
        /// <param name="username">Nombre de usuario del atleta.</param>
        /// <param name="authService">Servicio de autenticación a utilizar.</param>
        public FormHomeAtleta(string username, IAuthService authService)
        {
            InitializeComponent();

            // Inicialización del panel de borde izquierdo para botones activos
            leftBorderBtn = new Panel
            {
                Size = new Size(7, 60)
            };
            panelMenu.Controls.Add(leftBorderBtn);

            // Asignar el nombre de usuario
            this.username = username;

            // Capitaliza la primera letra del nombre de usuario
            lblUsername.Text = CapitalizeFirstLetter(username);

            // Asignar el servicio de autenticación
            _authService = authService;            
        }

        /// <summary>
        /// Capitaliza la primera letra de una cadena.
        /// </summary>
        /// <param name="input">La cadena a capitalizar.</param>
        /// <returns>La cadena con la primera letra en mayúscula.</returns>
        private string CapitalizeFirstLetter(string input)
        {
            if (string.IsNullOrEmpty(input))
                return input;

            return char.ToUpper(input[0]) + input.Substring(1).ToLower();
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
                FormUtilities.DisableButton(previousBtn, ThemeColor.Atleta.DefaultColor); // Desactiva el botón anterior
                FormUtilities.ActivateButton(
                    currentBtn,
                    leftBorderBtn,
                    iconCurrentChildForm,
                    lblSeleccion,
                    ThemeColor.Atleta.DefaultColor,
                    ThemeColor.Atleta.Color2);
                FormUtilities.OpenChildForm(ref currentChildForm, childForm, panelDesktop, lblSeleccion); // Abre el formulario hijo 
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
        /// Activa el botón de competiciones y cambia el color del icono y texto del botón.
        /// </summary>
        private void iconButtonCompetitions_Click(object sender, EventArgs e)
        {
            //ActivateButton(sender, ThemeColor.Atleta.Color2); //RGBColors.color2);            
            ActivateButtonAndOpenForm((IconButton)sender, new ProfileForm(_authService));
        }

        /// <summary>
        /// Activa el botón de entrenamientos y cambia el color del icono y texto del botón.
        /// </summary>
        private void iconButtonTraining_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new TrainerForm(_authService)); 
            //ActivateButton(sender, ThemeColor.Atleta.Color2);
        }

        /// <summary>
        /// Activa el botón de calendario y cambia el color del icono y texto del botón.
        /// </summary>
        private void iconButtonCalendar_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new ProfileForm(_authService));
        }

        /// <summary>
        /// Activa el botón de perfil y cambia el color del icono y texto del botón.
        /// </summary>
        private void iconButtonProfile_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new ProfileForm(_authService));
        }

        /// <summary>
        /// Activa el botón de eventos, cambia el color del icono y texto del botón, y abre el formulario de eventos.
        /// </summary>
        private void iconButtonEvent_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new EventForm(_authService));
        }

        /// <summary>
        /// Activa el botón de tienda y cambia el color del icono y texto del botón.
        /// </summary>
        private void iconButtonShop_Click(object sender, EventArgs e)
        {
            ActivateButtonAndOpenForm((IconButton)sender, new ProfileForm(_authService));
        }

        /// <summary>
        /// Restablece el estado del botón y del formulario a su estado inicial cuando se hace clic en el logo de la aplicación.
        /// </summary>
        private void pictureBox1_Click(object sender, EventArgs e)
        {
            // Cierra el formulario hijo actual si existe
            if (currentChildForm != null)
            {
                currentChildForm.Close();
            }

            // Cierra el formulario actual
            this.Hide();

            // Abre una nueva instancia de FormHomeAtleta
            FormHomeAtleta formHomeAtleta = new FormHomeAtleta(username, _authService);
            formHomeAtleta.Show();
        }

        /// <summary>
        /// Cierra la aplicación cuando se hace clic en el botón de salir.
        /// </summary>
        private void btnExit_Click(object sender, EventArgs e)
        {
            FormActions.ExitApplication();
        }

        /// <summary>
        /// Alterna el estado de la ventana entre maximizado y restaurado al hacer clic en el botón de maximizar.
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
        /// Permite arrastrar el formulario desde la barra de título cuando se presiona el mouse.
        /// </summary>
        private void panelTitleBar_MouseDown(object sender, MouseEventArgs e)
        {
            FormActions.HandleTitleBarDrag(this);
        }

        /// <summary>
        /// Cierra la aplicación cuando se hace clic en el botón de cerrar sesión.
        /// </summary>
        private void iconButtonLogout_Click(object sender, EventArgs e)
        {
            this.Hide();
            // Crear y mostrar el nuevo formulario (FormHome)
            FormHome formHome = new FormHome(_authService);
            formHome.Show();
        }
    }
}
