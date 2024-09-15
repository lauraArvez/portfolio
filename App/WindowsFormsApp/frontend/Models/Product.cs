using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InterfazRest.Models
{
    public class Product
    {
        public string Name { get; set; }       // Nombre del producto
        public string Description { get; set; } // Descripción del producto
        public string ImagePath { get; set; }   // Ruta de la imagen
        public string PurchaseLink { get; set; } // Enlace para la compra (opcional)
    }

}
