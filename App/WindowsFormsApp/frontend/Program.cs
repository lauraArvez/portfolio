using InterfazRest.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InterfazRest
{
    internal static class Program
    {
        /// <summary>
        /// Punto de entrada principal para la aplicación.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // Configuración del cliente HTTP y del servicio de autenticación
            var httpClient = new HttpClient();
            var authService = new AuthService(httpClient);

            // Habilita los estilos visuales y configura el renderizado de texto predeterminado
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Inicia la aplicación con el formulario principal (FormHome), inyectando el servicio de autenticación
            Application.Run(new FormHome(authService));
        }
    }
}
