const signUpBtn = document.getElementById('signUp');
const signInBtn = document.getElementById('signIn');
const container = document.getElementById('container');
const NumeroCelular = document.getElementById('NumeroCelular');

signUpBtn.addEventListener('click', () => {
  container.classList.add('right-panel-active');
});

signInBtn.addEventListener('click', () => {
  container.classList.remove('right-panel-active');
});

NumeroCelular.addEventListener('input', function() {
    let formattedNumber = this.value.replace(/\D/g, '');
    if (formattedNumber.length > 0) {
        formattedNumber = formattedNumber.match(/(\d{0,3})(\d{0,3})(\d{0,4})/);
        this.value = !formattedNumber[2] ? formattedNumber[1] : formattedNumber[1] + '-' + formattedNumber[2] + (formattedNumber[3] ? '-' + formattedNumber[3] : '');
    }
});

$(document).ready(function() {
  $('#departamentoLista').change(function() {
    var departamentoId = $(this).val();
    $.ajax({
      url: '/Ciudades/' + departamentoId,
      type: 'GET',
      success: function(data){
        $('#ciudadLista').empty();
        $.each(data, function(index, ciudad){
          $('#ciudadLista').append('<option value="' + ciudad.id_Ciudad + '">' + ciudad.nombreCiu + '</option>');
          //console.log("------> "+ ciudad.nombreCiu);
          //console.log("------> "+ ciudad.id_Ciudad);
        });
      }
    });
  });
});

function calculateTotal() {
  var cantidadTotal = document.getElementById("Cantidad_total").value;
  var precioUnitario = document.getElementById("Preciounitario").innerText;
  var precioTotal = cantidadTotal * parseFloat(precioUnitario);
  document.getElementById("Precio_total").value = precioTotal;
}

function agregarDetalle(idProducto, button) {
  var row = button.closest('tr');
  var cantidad = row.querySelector('.CantidadDetalle').value;
  
  var cantidad2 = row.querySelector('.CantidadDetalle');
  cantidad2.disabled = true;

  var xhr = new XMLHttpRequest();
  xhr.open("GET", '/Gestion_Inventarios/Pedido/DetallePedido/' + encodeURIComponent(idProducto) + '/' + encodeURIComponent(cantidad), true);
  xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200) {
          button.disabled = true;
          button.textContent = "AGREGADO"

          var botonEliminar = document.getElementById('botonEliminar_' + idProducto);
          botonEliminar.disabled = false;
      }
  };
  xhr.send();
}

function eliminarDetalle(idProducto, button) {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", '/Gestion_Inventarios/Pedido/DetallePedido/Eliminar/' + encodeURIComponent(idProducto), true);
  xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var botonAgregar = document.getElementById('botonAgregar_' + idProducto);
        botonAgregar.disabled = false;
        botonAgregar.textContent = "AGREGAR";

        var botonEliminar = document.getElementById('botonEliminar_' + idProducto);
        botonEliminar.disabled = true;

        var row = button.closest('tr');
        var cantidad2 = row.querySelector('.CantidadDetalle');
        cantidad2.disabled = false;
      }
  };
  xhr.send();
}


function regresar() {
  window.history.back();
}

function buscarProductos() {
  var keyword = document.getElementById("barraBusqueda").value;
  fetch(`/Gestion_Inventarios/Productos/Busqueda?nombre=` + keyword)
      .then(response => response.json())
      .then(data => {
          console.log(data);  // Verifica la estructura del JSON devuelto
          var table = document.getElementById("tablaProductos");
          table.innerHTML = '';
          data.forEach(product => {
              table.innerHTML += `
                  <tr>
                      <td>${product.id_Producto}</td>
                      <td>${product.usuario.nom_usuario}</td>
                      <td>${product.nombre}</td>
                      <td>${product.descripcion}</td>
                      <td>${product.cantidad}</td>
                      <td>${product.preciounitario}</td>
                      <td>${product.proveedor.nom_usuario}</td>
                      <td><a href="/Gestion_Inventarios/Productos/BorrarProducto/${product.id_Producto}"><button type="button">BORRAR</button></a></td>
                      <td><a href="/Gestion_Inventarios/Productos/EditarProducto/${product.id_Producto}"><button type="button">EDITAR</button></a></td>
                  </tr>
              `;
          });
      });
}

function buscarProductosPro() {
  var keyword = document.getElementById("barraBusqueda").value;
  fetch(`/Gestion_Inventarios/Tienda/Busqueda?nombre=` + keyword)
      .then(response => response.json())
      .then(data => {
          var table = document.getElementById("tablaProductos");
          table.innerHTML = '';
          data.forEach(product => {
              table.innerHTML += `
              <tr>
                  <td>${product.id_Producto}</td>
                  <td>${product.nombre}</td>
                  <td>${product.descripcion}</td>
                  <td>${product.cantidad}</td>
                  <td>${product.preciounitario}</td>
                  <td><a href="/Gestion_Inventarios/Usuario/Proveedor/${product.proveedor.id_Usuario}">${product.proveedor.nom_usuario}</a></td>
                  <td><a href="/Gestion_Inventarios/Transaccion/${product.id_Producto}"><button type="button">COMPRAR</button></a></td>
              </tr>
              `;
          });
      });
}

function filterProducts() {
  var proveedor = document.getElementById("filtro").value;
  fetch(`/Gestion_Inventarios/Productos/filtro?proveedor=` + proveedor)
      .then(response => response.json())
      .then(data => {
          var table = document.getElementById("tablaProductos");
          table.innerHTML = '';
          data.forEach(product => {
              table.innerHTML += `
                  <tr>
                      <td>${product.id_Producto}</td>
                      <td>${product.usuario.nom_usuario}</td>
                      <td>${product.nombre}</td>
                      <td>${product.descripcion}</td>
                      <td>${product.cantidad}</td>
                      <td>${product.preciounitario}</td>
                      <td>${product.proveedor.nom_usuario}</td>
                      <td><a href="/Gestion_Inventarios/Productos/BorrarProducto/${product.id_Producto}"><button type="button">BORRAR</button></a></td>
                      <td><a href="/Gestion_Inventarios/Productos/EditarProducto/${product.id_Producto}"><button type="button">EDITAR</button></a></td>
                  </tr>
              `;
          });
      });
}

function filterProductsPro() {
  var proveedor = document.getElementById("filtro").value;
  fetch(`/Gestion_Inventarios/Tienda/filtro?proveedor=` + proveedor)
      .then(response => response.json())
      .then(data => {
          var table = document.getElementById("tablaProductos");
          table.innerHTML = '';
          data.forEach(product => {
              table.innerHTML += `
                  <tr>
                      <td>${product.id_Producto}</td>
                      <td>${product.nombre}</td>
                      <td>${product.descripcion}</td>
                      <td>${product.cantidad}</td>
                      <td>${product.preciounitario}</td>
                      <td><a href="/Gestion_Inventarios/Usuario/Proveedor/${product.proveedor.id_Usuario}">${product.proveedor.nom_usuario}</a></td>
                      <td><a href="/Gestion_Inventarios/Transaccion/${product.id_Producto}"><button type="button">COMPRAR</button></a></td>
                  </tr>
              `;
          });
      });
}







