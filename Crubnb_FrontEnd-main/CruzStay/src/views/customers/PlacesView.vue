<template>
    <b-container fluid class="mt-4">
        <b-row class="mb-5 justify-content-md-center">
            <!-- Modal Solicitar Lugar -->
            <DoRequest :datosLugar="lugar"></DoRequest>

            <b-col col md="6">
                <b-img :src="lugar.imagen" :alt="lugar.nombre"
                    style="max-width: 300px; max-height: 415px; min-width: 100%; min-height: 100%; border-radius: 10px;"></b-img>
            </b-col>
            <b-col col md="5">
                <div>
                    <p class="mb-3 h3">{{ lugar.nombre }}</p>
                </div>
                <div id="stars" class="mt-3">
                    <b-icon-star-fill v-for="i in Math.floor(lugar.rate.stars)" :key="'fill-' + i"
                        class="stars"></b-icon-star-fill>
                    <b-icon-star-half v-if="lugar.rate.stars % 1 !== 0" :key="'half'" class="stars"></b-icon-star-half>
                    <b-icon-star v-for="j in 5 - Math.ceil(lugar.rate.stars)" :key="'empty-' + j"
                        class="stars"></b-icon-star>
                    <p>{{ lugar.rate.stars }} estrellas de {{ lugar.rate.totalReviews }} reseñas</p>
                </div>
                <div id="vendedorInfo" class="mt-4">
                    <b-list-group-item class="d-flex align-items-center">
                        <b-avatar :src="lugar.vendedor.foto" :alt="lugar.vendedor.nombre" size="3rem"></b-avatar>
                        <div style="margin-left: 1.5%; margin-top: 2%;">
                            <span class="mr-auto">{{ lugar.vendedor.nombre }}</span>
                            <p>{{ lugar.vendedor.experiencia }} meses de experiencia</p>
                        </div>
                    </b-list-group-item>
                </div>
                <div id="datosCompra" class="mt-2">
                    <b-card>
                        <b-container>
                            <b-row>
                                <b-card-title>
                                    <h1 style="display: inline;">${{ parseFloat(lugar.precio).toFixed(2) }} </h1>
                                    <p style="display: inline;">Noche</p>
                                </b-card-title>
                                <b-card-text>
                                    <p>
                                        Costo por huesped, da click en solicitar espacio para realizar la cotizacion
                                    </p>
                                    <b-button class="btn-pinky mb-3 btn btn-big" v-b-modal.modal-lg>Solicitar
                                        espacio</b-button>
                                </b-card-text>
                            </b-row>
                        </b-container>
                    </b-card>
                </div>
            </b-col>
        </b-row>
        <b-row class="mt-5 justify-content-md-center">
            <b-col col sm="6">
                <div>
                    <h3>Descripción</h3>
                    <p class="my-justified-text">{{ lugar.descripcion }}</p>
                </div>
                <div class="mt-4">
                    <h3>Detalles</h3>
                    <b-row>
                        <b-col col sm="3">
                            <p>Habitaciones: {{ lugar.habitaciones }}</p>
                        </b-col>
                        <b-col col sm="3">
                            <p>Camas: {{ lugar.camas }}</p>
                        </b-col>
                        <b-col col sm="3">
                            <p>Baños: {{ lugar.banos }}</p>
                        </b-col>
                        <b-col col sm="3">
                            <p>Amueblado: {{ lugar.amueblado ? 'Si' : 'No' }}</p>
                        </b-col>
                    </b-row>
                </div>
                <!-- <div class="mt-4">
                    <h3>Ubicación</h3>
                    <p>{{ lugar.ubicacion }}</p>
                    <b-button href="#" class="btn-pinky"
                        style="width: 100%; height: 60px; font-size: 1.5rem; font-weight: bold;">Ver en mapa</b-button>
                </div> -->
                <div class="mt-4">
                    <h3>Servicios</h3>
                    <b-card v-for="(servicio, index) in lugar.servicios" :key="index" class="mb-3">
                        <b-container>
                            <b-row class="mt-2">
                                <b-col col sm="1">
                                    <b-icon :icon="servicio.icon" style="width: 30px; height: 30px;"></b-icon>
                                </b-col>
                                <b-col col sm="9">
                                    <p class="services-text">{{ servicio.nombre }}</p>
                                </b-col>
                            </b-row>
                        </b-container>
                    </b-card>
                </div>

            </b-col>
            <b-col col sm="5">
                <h3>Reseñas</h3>
                <b-card v-for="(review, index) in lugar.rate.reviews" :key="index" class="mb-3">
                    <b-list-group-item class="d-flex align-items-center">
                        <b-avatar :src="lugar.rate.reviews[index].foto" :alt="lugar.vendedor.nombre"
                            size="2.5rem"></b-avatar>
                        <div style="margin-left: 1.5%; ">
                            <span class="mr-auto" style="font-size: 1.2rem;">{{ lugar.rate.reviews[index].user }}</span>
                        </div>
                    </b-list-group-item>
                    <div class="mt-2" itemid="stars-reviewsred">
                        <div id="stars-reviews" style="display: inline;">
                            <b-icon-star-fill v-for="i in Math.floor(review.stars)" :key="'r-fill-' + i"
                                class="stars-r"></b-icon-star-fill>
                            <b-icon-star-half v-if="review.stars % 1 !== 0" :key="'r-half'"
                                class="stars-r"></b-icon-star-half>
                            <b-icon-star v-for="j in 5 - Math.ceil(review.stars)" :key="'r-empty-' + j"
                                class="stars-r"></b-icon-star>
                        </div>
                        <p style="display: inline; margin-left: 2%;">{{ review.fecha }}</p>
                    </div>

                    <b-row class="mt-1">
                        <b-col col sm="12">
                            <p class="my-justified-text">{{ review.review }}</p>
                        </b-col>
                    </b-row>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import DoRequest from './modals/DoRequest.vue';
export default {
    name: 'DetallesEspacio',
    components: {
        DoRequest,
    },
    computed: {
    },
    data() {
        return {
            lugar:
            {
                id: 1,
                nombre: 'Alojamiento entero: villa en San Benito, México SAC',
                precio: 300.50,
                ubicacion: 'Tlaxcala',
                imagen: 'https://picsum.photos/512/240/?image=28',
                cantidadMaximaPersonas: 4,
                habitaciones: 3,
                banos: 2,
                camas: 4,
                amueblado: 1,
                descripcion: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
                vendedor: {
                    nombre: 'Juan',
                    foto: 'https://picsum.photos/200/300/?image=1',
                    experiencia: 3, //meses
                },
                rate: {
                    stars: 2.8,
                    totalReviews: 4,
                    reviews: {
                        1: {
                            user: 'Mitosenio',
                            foto: 'https://picsum.photos/200/300/?image=8',
                            stars: 1.5,
                            review: 'Muy malo, la calera no funcionaba, habia goteria en el techo',
                            fecha: '2021-08-01',
                        },
                        2: {
                            user: 'Pedro',
                            foto: 'https://picsum.photos/200/300/?image=2',
                            stars: 2.5,
                            review: 'Bien, Muy bonito muy fresco. no es lo que esperaba pero estuvo bien',
                            fecha: '2021-08-02',
                        },
                        3: {
                            user: 'Maria',
                            foto: 'https://picsum.photos/200/300/?image=3',
                            stars: 3.5,
                            review: 'Regula, El lugar es muy limpio y bien ubicado, volvere a rentar la proxima vez que renter',
                            fecha: '2021-08-03',
                        },
                        4: {
                            user: 'Ana',
                            foto: 'https://picsum.photos/200/300/?image=4',
                            stars: 4,
                            review: 'Bueno, El mejor lugar para desestresarse, muy tranquilo y comodo, volvere a rentar en el futuro',
                            fecha: '2021-08-04',
                        }
                    }
                },
                servicios: {
                    1: {
                        id: 1,
                        nombre: 'Wifi',
                        icon: 'wifi',
                    },
                    2: {
                        id: 2,
                        nombre: 'Estacionamiento',
                        icon: 'house-door-fill',
                    },
                    3: {
                        id: 3,
                        nombre: 'Cocina',
                        icon: 'cup-fill',
                    },
                    4: {
                        id: 4,
                        nombre: 'TV',
                        icon: 'tv',
                    },
                    5: {
                        id: 5,
                        nombre: 'Aire acondicionado',
                        icon: 'wind',
                    },
                    6: {
                        id: 6,
                        nombre: 'Calefacción',
                        icon: 'thermometer',
                    },
                },

            },

        };
    },
};
</script>

<style>
.stars {
    font-size: 2.5rem;
    color: #50ACA1;
}

.stars-r {
    font-size: 1.2rem;
    color: #50ACA1;
}

.services-text {
    font-size: 1.2rem;
    font-weight: bold;
}
</style>