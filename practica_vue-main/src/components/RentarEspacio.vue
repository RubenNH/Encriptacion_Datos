<template>
    <div>
      <template>
        <div>
          <b-container fluid>
            <b-breadcrumb :items="items"></b-breadcrumb>

            <h2>Rentar un espacio</h2>
            <h4>Conoce nuestro Catalogo y encuentra el espacio que necesitas para tu evento.</h4>
            <b-button href="/#/CatalogoEspacios" variant="primary">Descubrir</b-button>
          </b-container>
        </div>
      </template>
    </div>
  </template>
    
  <script>
  
  export default {
    name: 'rentarEspacio',
  
    data() {
      return {
        items: [
          {
            text: "Lands Page",
            to: { name: "LandsPage" },
          },
          {
            text: "Rentar un espacio",
            active: true,
          },
        ],
      }
    }
  }
  </script>
    
                                                             