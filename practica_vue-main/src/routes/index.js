import VueRouter from "vue-router";
import Vue from "vue";

Vue.use(VueRouter);

const routes = [
    //la ruta base
    {
        path: "/",
        redirect: "/peliculas",
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
                path: "/peliculas",
                name: "peliculas",
                component: () =>
                    import ("../components/Peliculas.vue")
            }
        ]
    }
];

const router = new VueRouter({
    routes
});

export default router;