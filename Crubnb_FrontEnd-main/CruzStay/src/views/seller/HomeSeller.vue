<template>
    <b-container fluid>
        <solicitudes-reservacion :solicitudes="solicitudesReservacion"></solicitudes-reservacion>
        <b-row class="justify-content-md-center">
            <b-col cols="11">
                <b-container fluid>
                    <b-row class="justify-content-md-center mt-3">
                        <b-col col md="4">
                            <h2>Mis lugares</h2>
                        </b-col>
                        <b-col col md="2" class="text-end mt-2">
                            <b-button class="btn-bluegreen" pill badge badge-variant="danger" v-b-modal.modal-1>
                                <b-icon icon="exclamation-circle-fill"></b-icon> Solicitudes 
                            </b-button>
                        </b-col>
                        <b-col col md="2" class="text-end mt-2">
                            <b-button class="btn-bluegreen" pill>
                                <b-icon icon="house-fill"></b-icon> Reservaciones 
                            </b-button>
                        </b-col>
                        <b-col col md="4" class="text-end mt-2">
                            <b-button class="btn-bluegreen" pill>
                                <b-icon icon="patch-plus"></b-icon> Agregar lugar
                            </b-button>
                        </b-col>
                    </b-row>
                    <b-row class="mt-3">
                        <b-col col md="3" v-for="lugar in lugares" :key="lugar.id" class="mb-3">
                            <b-card style="max-width: 100%;" no-body class="overflow-hidden">
                                <b-card-img :src="lugar.imagen" alt="Image" class="rounded-0 default-card-image">
                                </b-card-img>
                                <b-card-body>
                                    <h4>{{ lugar.nombre }}</h4>
                                    <h6 style="margin-top: -2%;">{{ lugar.ciudad }}, {{ lugar.estado }}, {{ lugar.pais }}
                                    </h6>
                                    <div>
                                        <b-badge pill v-for="categoria in lugar.categorias" :key="categoria.id"
                                            class="btn-peach mb-2" style="margin-right: 1%;">{{ categoria.nombre
                                            }}</b-badge>
                                    </div>
                                    <div id="caalificacion">
                                        <div id="stars" style="display: inline; ">
                                            <b-icon-star-fill v-for="i in Math.floor(lugar.stars)" :key="'fill-' + i"
                                                class="my-stars"></b-icon-star-fill>
                                            <b-icon-star-half v-if="lugar.stars % 1 !== 0" :key="'half'"
                                                class="my-stars"></b-icon-star-half>
                                            <b-icon-star v-for="j in 5 - Math.ceil(lugar.stars)" :key="'empty-' + j"
                                                class="my-stars"></b-icon-star>
                                        </div>
                                        <p style=" display: inline;"> {{ lugar.stars }} estrellas</p>
                                    </div>
                                    <div id="precio" class="mt-2 mb-3">
                                        <h2 style="display: inline;" class="mt-2">${{ parseFloat(lugar.precio).toFixed(2) }}
                                        </h2>
                                        <p style="display: inline;">Noche</p>
                                    </div>
                                    <b-row>
                                        <b-col col md="8">
                                            <b-button href="#" class="btn-pinky mb-2" style="max-width: 100%; min-width: 100%"><b-icon
                                                    icon="pencil-square"></b-icon> Editar</b-button>
                                        </b-col>
                                        <b-col col md="4">
                                            <b-button href="#" class="btn-pinky mb-2" style="max-width: 100%; min-width: 100%"><b-icon
                                                    icon="trash"></b-icon>
                                            </b-button>
                                        </b-col>
                                    </b-row>
                                </b-card-body>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-container>
            </b-col>
        </b-row>
    </b-container>
</template>
<script>
import SolicitudesReservacion from './modals/SolicitudesReservacion.vue';
export default {
  components: { SolicitudesReservacion },
    data() {
        return {
            lugares: [
                {
                    id: 1,
                    nombre: 'Casa de campo',
                    descripcion: 'Casa de campo con alberca',
                    stars: 4.5,
                    precio: 100,
                    cantidadMaximaPersonas: 10,
                    pais: 'México',
                    estado: 'Jalisco',
                    ciudad: 'Guadalajara',
                    direccion: 'Calle 123, Colonia 456, Ciudad 789',
                    imagen: 'https://picsum.photos/600/300/?image=78',
                    categorias: [
                        {
                            id: 1,
                            nombre: 'Casa completa',
                        },
                        {
                            id: 2,
                            nombre: 'PLayas',
                        },
                        {
                            id: 3,
                            nombre: 'Alternativa',
                        }
                    ]
                },
                {
                    id: 2,
                    nombre: 'Casa de playa',
                    descripcion: 'Casa de playa con alberca',
                    stars: 3.5,
                    precio: 200,
                    cantidadMaximaPersonas: 20,
                    pais: 'México',
                    estado: 'Guerrero',
                    ciudad: 'Acapulco',
                    imagen: 'https://picsum.photos/1200/600/?image=20',
                    categorias: [
                        {
                            id: 2,
                            nombre: 'PLayas',
                        },
                        {
                            id: 3,
                            nombre: 'Alternativa',
                        }
                    ]
                },
                {
                    id: 3,
                    nombre: 'Casa de ciudad',
                    descripcion: 'Casa de ciudad con alberca',
                    stars: 4.5,
                    precio: 300.5,
                    cantidadMaximaPersonas: 30,
                    pais: 'México',
                    estado: 'Guanajuato',
                    ciudad: 'León',
                    imagen: 'https://picsum.photos/1600/800/?image=88',
                    categorias: [
                        {
                            id: 1,
                            nombre: 'Casa completa',
                        },
                        {
                            id: 3,
                            nombre: 'Alternativa',
                        }
                    ]
                },
                {
                    id: 4,
                    nombre: 'Casa de montaña',
                    descripcion: 'Casa de montaña con alberca',
                    stars: 5,
                    precio: 405,
                    cantidadMaximaPersonas: 40,
                    pais: 'México',
                    estado: 'Morelos',
                    ciudad: 'Cuernavaca',
                    imagen: 'https://picsum.photos/2400/1200/?image=99',
                    categorias: [
                        {
                            id: 1,
                            nombre: 'Casa completa',
                        },
                        {
                            id: 3,
                            nombre: 'Alternativa',
                        },
                        {
                            id: 4,
                            nombre: 'Montaña',
                        }
                    ]

                }
            ],
            solicitudesReservacion: [
                {
                    idSoliciud: 1,
                    lugar:{
                        idLugar: 1,
                        nombre: 'Casa de campo',
                    },
                    cliente: {
                        idCliente: 1,
                        nombre: 'Juan Perez',
                        foto: 'https://placekitten.com/300/300',
                        stars: 4.5,
                        reservacionesPasadas: 5,
                    },
                    fechaInicio: '24/02/2024',
                    fechaFin: '30/02/2024',
                    diasEstancia: 6,
                    cantidadPersonas: 3,
                    total: 5463.09,
                },
                {
                    idSoliciud: 2,
                    lugar:{
                        id: 2,
                        nombre: 'Casa de playa',
                    },
                    cliente: {
                        id: 2,
                        nombre: 'Maria Lopez',
                        foto: 'https://picsum.photos/200/200/?image=2',
                        stars: 3.5,
                        reservacionesPasadas: 3,
                    },
                    fechaInicio: '24/02/2024',
                    fechaFin: '30/02/2024',
                    diasEstancia: 6,
                    cantidadPersonas: 3,
                    total: 5463.09,
                },
                {
                    idSoliciud: 3,
                    lugar:{
                        id: 3,
                        nombre: 'Casa de ciudad',
                    },
                    cliente: {
                        id: 3,
                        nombre: 'Pedro Ramirez',
                        foto: 'https://picsum.photos/200/200/?image=3',
                        stars: 4.5,
                        reservacionesPasadas: 7,
                    },
                    fechaInicio: '24/02/2024',
                    fechaFin: '30/02/2024',
                    diasEstancia: 6,
                    cantidadPersonas: 3,
                    total: 205463.09,
                },
                {
                    idSoliciud: 4,
                    lugar:{
                        id: 4,
                        nombre: 'Super Palapa para descanso y fiestas de cualquier indole y tamaño',
                    },
                    cliente: {
                        id: 4,
                        nombre: 'Ana Torres',
                        foto: 'https://placekitten.com/300/300',
                        stars: 5,
                        reservacionesPasadas: 10,
                    },
                    fechaInicio: '24/02/2024',
                    fechaFin: '30/02/2024',
                    diasEstancia: 6,
                    cantidadPersonas: 3,
                    total: 15463.09,
                }
            ]
        }
    }
}
</script>

<style>
.default-card-image {
    max-width: 100%;
    max-height: 500px;
    min-width: 100%;
    min-height: 100%;
}

.my-stars {
    font-size: 1.1rem;
    color: #e99675;
}
</style>
