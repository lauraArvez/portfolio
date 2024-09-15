using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static InterfazRest.LoginAdminForm;
namespace InterfazRest.Services
{   
    /// <summary>
    /// Define la interfaz para los servicios de autenticación, proporcionando
    /// métodos para el manejo de login, tokens de autenticación y verificación del estado de autenticación.
    /// </summary>
    public interface IAuthService
    {
        /// <summary>
        /// Realiza la autenticación del usuario mediante su nombre de usuario y contraseña.
        /// </summary>
        /// <param name="username">El nombre de usuario del usuario.</param>
        /// <param name="password">La contraseña del usuario.</param>
        /// <returns>Un objeto <see cref="LoginResult"/> que indica el resultado de la autenticación.</returns>
        Task<LoginResult> LoginAsync(string username, string password);

        /// <summary>
        /// Establece el token de autenticación para el usuario autenticado.
        /// </summary>
        /// <param name="token">El token de autenticación.</param>
        void SetToken(string token);

        /// <summary>
        /// Obtiene el token de autenticación actual.
        /// </summary>
        /// <returns>El token de autenticación.</returns>
        string GetToken();

        /// <summary>
        /// Verifica si el usuario está autenticado.
        /// </summary>
        /// <returns><c>true</c> si el usuario está autenticado; de lo contrario, <c>false</c>.</returns>
        bool IsAuthenticated();
    }
}
