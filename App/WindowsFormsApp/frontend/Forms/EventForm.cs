using InterfazRest.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InterfazRest
{
    public partial class EventForm : Form
    {
        // Servicio de autenticación inyectado
        private readonly IAuthService _authService;

        /// <summary>
        /// Inicializa una nueva instancia de la clase <see cref="EventForm"/> con el servicio de autenticación especificado.
        /// </summary>
        public EventForm(IAuthService authService)
        {
            InitializeComponent();
            _authService = authService;
        }
    }
}
