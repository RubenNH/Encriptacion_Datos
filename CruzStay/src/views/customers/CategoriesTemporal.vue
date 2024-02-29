<!-- 
    Este archivo es el encargado de mostrar las categorías de los productos, permitiendo crear, editar y cambiar el estado de las categorías. 
    Se hace uso de la librería de BootstrapVue para mostrar la tabla de categorías y los formularios para crear y editar categorías.
    Se hace uso de la librería de Axios para realizar las peticiones HTTP.
    Se hace uso de la librería de EncryptionService para encriptar y desencriptar los datos.
    La uttima es un archivo propio que permite obtener la clave secreta para encriptar y desencriptar los datos.
    La clave secreta es almacenada en un almacenador de la librería de Pinia.
    Se hace uso de la librería de Pinia para almacenar la clave secreta.

    Cuando importamos useSecret de "@/stores/key" estamos importando el almacenador de la clave secreta.
    con ella podemos mandar la llave a los metodos de encriptacion y desencriptacion. que retornan la clave encriptada o desencriptada.

    En el metodo getCategories() se hace una peticion GET a la API de categorias, se obtiene la respuesta y se desencripta el campo 'name' de cada categoria.
    En el metodo createCategory() se encripta el nombre de la categoria y se manda a la API para crear una nueva categoria.
    En el metodo editCategory() se encripta el id y el nombre de la categoria y se manda a la API para editar una categoria.
    En el metodo deleteCategory() se encripta el id y el nombre de la categoria y se manda a la API para cambiar el estado de una categoria.
-->
<template>
    <b-container>
        <b-row>
            <b-col>
                <h1>Categories</h1>
                <b-card>
                    <h4>Crear categoria</h4>
                    <b-form @submit="createCategory">
                        <b-form-group label="Category Name:" label-for="categoryName">
                            <b-form-input id="categoryName" v-model="categoryName" required></b-form-input>
                        </b-form-group>
                        <b-button type="submit" variant="primary">Create</b-button>
                    </b-form>
                </b-card>

            </b-col>
        </b-row>
        <b-row class="mt-3">
            <b-col>
                <b-table striped hover :items="categoryArray" :fields="fields">
                    <!-- //make name field editable -->
                    <template v-slot:cell(name)="data">
                        <b-form-input v-model="data.item.name"></b-form-input>
                    </template>
                    <template v-slot:cell(actions)="data">
                        <b-button variant="primary" @click="editCategory(data.item.id)">Edit</b-button>
                        <b-button variant="danger" @click="deleteCategory(data.item.id)">Cambiar estado</b-button>
                    </template>
                </b-table>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import { useSecret } from "@/stores/key";
export default {
    mounted() {
        this.key = useSecret();
        this.getCategories();
    },
    data() {
        return {
            key: "",
            categoryName: "",
            categoryArray: [],
            fields: [
                { key: "id", label: "ID" },
                { key: "name", label: "Name" },
                { key: "status", label: "Status" },
                { key: "actions", label: "Actions" }
            ],
        };
    },
    methods: {
        getCategories() {
            //fetch http://localhost:8080/api/category/
            //get the response and decrypt 'name' field
            this.$axios.get('http://localhost:8080/api/category/')
                .then(response => {
                    this.categoryArray = response.data.data || [];
                    this.categoryArray.forEach(category => {
                        category.name = this.$encryptionService.decryptData(category.name, this.key);
                    });


                })
                .catch(error => {
                    console.log(error);
                });;
        },
        createCategory() {
            const encryptedName = this.$encryptionService.encryptData(this.categoryName, this.key);
            console.log("Encrypted Name:", encryptedName);

            //post http://localhost:8080/api/category/
            //send the encrypted name
            this.$axios.post('http://localhost:8080/api/category/', {
                name: encryptedName
            })
                .then(response => {
                    console.log(response);
                    this.getCategories();
                })
                .catch(error => {
                    console.log(error);
                });
        },
        editCategory(id) {
            //encrypt the id
            const encryptedId = this.$encryptionService.encryptData(id.toString(), this.key);
            //encrypt the new name
            const encryptedName = this.$encryptionService.encryptData(this.categoryArray.find(category => category.id === id).name, this.key);
            this.$axios.put('http://localhost:8080/api/category/' + encryptedId, {
                name: encryptedName,
                status: this.categoryArray.find(category => category.id === id).status
            })
                .then(response => {
                    console.log(response);
                    this.getCategories();
                })
                .catch(error => {
                    console.log(error);
                });
        },
        deleteCategory(id) {
            //encrypt the id
            const encryptedId = this.$encryptionService.encryptData(id.toString(), this.key);
            const encryptedName = this.$encryptionService.encryptData(this.categoryArray.find(category => category.id === id).name, this.key);
            this.$axios.put('http://localhost:8080/api/category/' + encryptedId, {
                name: encryptedName,
                status: this.categoryArray.find(category => category.id === id).status === true ? false : true,

            })
                .then(response => {
                    console.log(response);
                    this.getCategories();
                })
                .catch(error => {
                    console.log(error);
                });
        },
    },
};
</script>

<style></style>