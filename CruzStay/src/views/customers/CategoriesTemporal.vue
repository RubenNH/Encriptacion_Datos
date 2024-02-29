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