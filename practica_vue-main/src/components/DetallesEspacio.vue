<template>
  <div>
    <b-container fluid>
      <b-breadcrumb :items="items" class="mb-4"></b-breadcrumb>
      <b-row class="justify-content-md-center">
        <b-col col lg="7">
          <img :src="selectedOferta.imagen" :alt="selectedOferta.nombre" style="max-width: 100%; max-height: 100%;" />
        </b-col>
        <b-col col lg="3">
          <h1 class="mb-2">{{ selectedOferta.nombre }}</h1>
          <h4>{{ selectedOferta.descripcion }}</h4>
          <h6>Precio: {{ selectedOferta.precio }}</h6>
          <h6>Ubicación: {{ selectedOferta.ubicacion }}</h6>
          <h6>Habitaciones: {{ selectedOferta.habitaciones }}</h6>
          <h6>Baños: {{ selectedOferta.banos }}</h6>
          <b-button class="mt-2" variant="primary">Reservar lugar</b-button>

        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
  
<script>
export default {
  name: 'DetallesEspacio',
  mounted() {
    const espacioId = parseInt(this.$route.params.id); // Convert to integer
    console.log('Espacio ID:', espacioId);
  },
  computed: {
    selectedOferta() {
      const espacioId = parseInt(this.$route.params.id);
      return this.ofertas.find(oferta => oferta.id === espacioId) || {};
    }
  },
  data() {
    return {
      items: [
        {
          text: 'Lands Page',
          to: {
            name: 'LandsPage'
          },
        },
        {
          text: "Rentar un espacio",
          to: {
            name: "RentarEspacio"
          },
        },
        {
          text: 'Catalogo de Espacios',
          to: {
            name: 'CatalogoEspacios'
          },
        },
        {
          text: 'Detalles del espacio',
          active: true
        }
      ],
      ofertas: [
        { id: 1, nombre: 'Casa de campo', descripcion: 'Casa de campo con alberca', precio: '$1000', ubicacion: 'Tlaxcala', imagen: 'https://picsum.photos/1024/480/?image=28', habitaciones: 3, banos: 2},
        { id: 2, nombre: 'Palapa', descripcion: 'Palapa con alberca', precio: '$2000', ubicacion: 'Tlaxcala', imagen: 'https://picsum.photos/1024/480/?image=13', habitaciones: 2, banos: 1},
        { id: 3, nombre: 'Salon de eventos', descripcion: 'Salon de eventos con alberca', precio: '$3000', ubicacion: 'Tlaxcala', imagen: 'https://picsum.photos/1024/480/?image=14', habitaciones: 1, banos: 1},
      ],
    };
  },
};
</script>
