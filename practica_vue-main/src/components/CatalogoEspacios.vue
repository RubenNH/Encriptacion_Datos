<template>
    <div>
        <b-container fluid>
            <b-breadcrumb :items="items" class="mb-2"></b-breadcrumb>

            <h1 class="text-center"> Encuentra el espacio que necesitas para tu evento</h1>

            <b-carousel id="carousel-fade" style="text-shadow: 0px 0px 2px #000" fade indicators img-width="1024"
                img-height="480">
                <b-carousel-slide img-src="https://picsum.photos/1024/250/?image=28"></b-carousel-slide>
                <b-carousel-slide img-src="https://picsum.photos/1024/250/?image=25"></b-carousel-slide>
                <b-carousel-slide img-src="https://picsum.photos/1024/250/?image=13"></b-carousel-slide>
                <b-carousel-slide img-src="https://picsum.photos/1024/250/?image=14"></b-carousel-slide>
                <b-carousel-slide img-src="https://picsum.photos/1024/250/?image=15"></b-carousel-slide>
            </b-carousel>

            <b-table striped hover :items="ofertas" class="mt-4">
                <template #cell(imagen)="data">
                    <img :src="data.value" alt="Espacio Image" style="max-width: 100px; max-height: 100px;" />
                </template>
                <template #cell(accion)="data">
                    <b-button @click="verDetalles(data.item)" variant="primary">Ver detalles</b-button>
                </template>
            </b-table>
        </b-container>
    </div>
</template>
  
<script>
export default {
    name: 'CatalogoEspacios',
    methods: {
        verDetalles(item) {
            // redirect to DetallesEspacio.vue and pass the item.id as a parameter
            this.$router.push({ name: 'DetallesEspacio', params: { id: item.id } });

            // or use this.$router.push('/DetallesEspacio/' + item.id);

        },
    },
    data() {
        return {
            items: [{
                text: 'Lands Page',
                to: { name: 'LandsPage'},
            },
            {
                text: "Rentar un espacio",
                to: { name: "RentarEspacio" },
            },
            {
                text: 'Catalogo de Espacios',
                active: true
            },
            ],
            ofertas: [
                { id: 1, nombre: 'Casa de campo', descripcion: 'Casa de campo con alberca', precio: '$1000', ubicacion: 'Tlaxcala', imagen: 'https://picsum.photos/1024/250/?image=28', accion: 'Ver detalles' },
                { id: 2, nombre: 'Palapa', descripcion: 'Palapa con alberca', precio: '$2000', ubicacion: 'Tlaxcala', imagen: 'https://picsum.photos/1024/250/?image=13', accion: 'Ver detalles' },
                { id: 3, nombre: 'Salon de eventos', descripcion: 'Salon de eventos con alberca', precio: '$3000', ubicacion: 'Tlaxcala', imagen: 'https://picsum.photos/1024/250/?image=14', accion: 'Ver detalles' },
            ],
        };
    },
};
</script>
 