import VueRouter from "vue-router";
import Vue from "vue";

Vue.use(VueRouter);

const routes = [
    //la ruta base
    {
        path: "/",
        redirect: "/inicio",
    },
    //rutas asociadas a los componentes
    {
        path: "/",
        component: {
            render(c) {
                return c("router-view");
            }
        },
        children: [
            {
                path: "/inicio",
                name: "inicio",
                component: () =>
                    import ("../components/Inicio.vue")
                
            },
            {
                path: "/main",
                name: "main",
                component: () =>
                    import ("../components/Main.vue")
            },
            {
                path: "/tercero",
                name: "tercero",
                component: () =>
                    import ("../components/Tercero.vue")
            },
            {
                path: "/LandsPage",
                name: "LandsPage",
                component: () =>
                    import ("../components/InicioLandspage.vue")
            },
            {
                path: "/RentarEspacio",
                name: "RentarEspacio",
                component: () =>
                    import ("../components/RentarEspacio.vue")
            },
            {
                path: "/RentarTuEspacio",
                name: "RentarTuEspacio",
                component: () =>
                    import ("../components/RentarTuEspacio.vue")
            },
            {
                path: "/CatalogoEspacios",
                name: "CatalogoEspacios",
                component: () =>
                    import ("../components/CatalogoEspacios.vue")
            },
            {
                path: "/DetallesEspacio",
                name: "DetallesEspacio",
                component: () =>
                    import ("../components/DetallesEspacio.vue")
            },
            {
                path: "/InfoEspacios",
                name: "InfoEspacios",
                component: () =>
                    import ("../components/InfoEspacios.vue")
            },
            {
                path: "/OfertarEspacio",
                name: "OfertarEspacio",
                component: () =>
                    import ("../components/OfertarEspacio.vue")
            },
            {
                path: "/formularioUsuario",
                name: "formularioUsuario",
                component: () =>
                    import ("../components/formularioUsuario.vue")
            },
            {
                path: "/peliculas",
                name: "peliculas",
                component: () =>
                    import ("../components/Peliculas.vue")
            },
            {
                path: "/dragndrop",
                name: "dragndrop",
                component: () =>
                    import ("../components/MoviesDragNDrop.vue")
            },
            {
                path: "/dragNDropCreateMovie",
                name: "dragNDropCreateMovie",
                component: () =>
                    import ("../components/DragNDropCreateMovie.vue")
            }
        ]
    }
];

const router = new VueRouter({
    routes
});

export default router;