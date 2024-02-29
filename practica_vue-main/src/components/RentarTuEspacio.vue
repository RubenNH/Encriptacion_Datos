<template>
    <div>
      <template>
        <div>
          <b-container fluid>
            <b-breadcrumb :items="items"></b-breadcrumb>

            <h2>Rentar Tu espacio</h2>
            <h4>Pon a rentar tu espacio a un precio justo a personas que lo necesitan.</h4>
            <b-button href="/#/InfoEspacios" variant="primary">Descubrir</b-button>
          </b-container>
        </div>
      </template>
    </div>
  </template>
    
  <script>
  
  export default {
    name: 'rentarTuEspacio',
  
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
    
                                                             