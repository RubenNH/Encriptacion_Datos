<template>
    <form @submit.prevent="createCategory">
        <input type="text" v-model="categoryName" />
        <button type="submit">Create Category</button>
    </form>
</template>

<script>
import { useSecret } from "@/stores/key";
export default {
    mounted() {
        let key = useSecret();

        //fetch http://localhost:8080/api/category/
        //get the response and decrypt 'name' field
        this.$axios.get('http://localhost:8080/api/category/')
            .then(response => {
                const categoryArray = response.data.data || [];

                categoryArray.forEach((category) => {
                    //set the decrypted name to the category object
                    category.name = this.$encryptionService.decryptData(category.name, key);
                });

                console.log(categoryArray);
            })
            .catch(error => {
                console.log(error);
            });

    },
    data() {
        return {
            categoryName: "",
        };
    },
    methods: {
        createCategory() {
            let key = useSecret();
            const encryptedName = this.$encryptionService.encryptData(this.categoryName, key);
            console.log("Encrypted Name:", encryptedName);

            //post http://localhost:8080/api/category/
            //send the encrypted name
            this.$axios.post('http://localhost:8080/api/category/', {
                name: encryptedName
            })
                .then(response => {
                    console.log(response);
                })
                .catch(error => {
                    console.log(error);
                });
        },
    },
};
</script>





<style></style>