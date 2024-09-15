using System.Drawing;
using System.Windows.Forms;

namespace InterfazRest.Utilities
{
    public static class LoginUtilities
    {
        private const int maxAttempts = 3; // Número máximo de intentos permitidos
        private static int loginAttempts = 0; // Contador de intentos de inicio de sesión fallidos

        /// <summary>
        /// Verifica si el usuario ha superado el número máximo de intentos de login.
        /// </summary>
        public static bool HasExceededMaxAttempts()
        {
            return loginAttempts >= maxAttempts;
        }

        /// <summary>
        /// Incrementa el contador de intentos de inicio de sesión fallidos.
        /// </summary>
        public static void IncrementLoginAttempts()
        {
            loginAttempts++;
        }

        /// <summary>
        /// Restablece el contador de intentos fallidos de inicio de sesión.
        /// </summary>
        public static void ResetLoginAttempts()
        {
            loginAttempts = 0;
        }

        /// <summary>
        /// Limpia los campos de entrada en el formulario de login.
        /// </summary>
        public static void ClearInputFields(TextBox username, TextBox password)
        {
            username.Text = string.Empty;
            password.Text = string.Empty;
            username.Focus();
        }

        // <summary>
        /// Muestra un ToolTip personalizado cuando se presiona la tecla Enter en un campo de texto.
        /// El ToolTip contiene un mensaje de advertencia y se muestra cerca del control especificado.
        /// </summary>
        /// <param name="e">Los argumentos del evento KeyDown que verifican si la tecla Enter ha sido presionada.</param>
        /// <param name="toolTip">El ToolTip que se mostrará con el mensaje.</param>
        /// <param name="control">El control cerca del cual aparecerá el ToolTip (e.g., un TextBox).</param>
        /// <param name="message">El mensaje a mostrar dentro del ToolTip.</param>
        public static void ShowTooltipOnEnter(KeyEventArgs e, ToolTip toolTip, Control control, string message)
        {
            if (e.KeyCode == Keys.Enter)
            {
                // Configuración predeterminada del ToolTip
                toolTip.ToolTipTitle = "Atención";
                toolTip.BackColor = Color.LightYellow;
                toolTip.ForeColor = Color.DarkRed;
                toolTip.IsBalloon = false; 
                toolTip.ShowAlways = true; // Mostrar el tooltip incluso si el control no tiene foco
                toolTip.ToolTipIcon = ToolTipIcon.Warning; // Icono de advertencia

                // Muestra el ToolTip cerca del control especificado
                toolTip.Show(message, control, 0, -40, 5000); // 5 segundos de duración

                // Prevenir que el evento KeyDown haga más acciones
                e.SuppressKeyPress = true;
            }
        }
    }    
}
