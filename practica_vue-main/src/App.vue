
<template>
  <div id="app">
    <div>
      <b-nav>
        <b-nav-item active>Active</b-nav-item>
        <b-nav-item>Link</b-nav-item>
        <b-nav-item>Another Link</b-nav-item>
        <b-nav-item disabled>Disabled</b-nav-item>
      </b-nav>
    </div>
    <b-container fluid>
      <b-row>
        <b-col md="2" class="sidebar px-0">
          <b-nav vertical>
            <b-nav-item>
              <b-link to="/inicio">Inicio</b-link>
            </b-nav-item>
            <b-nav-item>
              <b-link to="/main">Main</b-link>
            </b-nav-item>
            <b-nav-item>
              <b-link to="/tercero">Tercero</b-link>
            </b-nav-item>
            <b-nav-item>
              <b-link to="/LandsPage">Landspage</b-link>
            </b-nav-item>
            <b-nav-item>
              <b-link to="/formularioUsuario">Formulario Usuario</b-link>
            </b-nav-item>
            <b-nav-item>
              <b-link to="/peliculas">Peliculas</b-link>
            </b-nav-item>
            <b-nav-item>
              <b-link to="/dragndrop">DragNDrop</b-link>
            </b-nav-item>
          </b-nav>
        </b-col>
        <b-col md="10">
          <router-view />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>



<style scoped>
#app {
  height: 97.6vh;
}

.sidebar {
  height: 94.8vh;
  background-color: #d6d6d6;
}
</style>
