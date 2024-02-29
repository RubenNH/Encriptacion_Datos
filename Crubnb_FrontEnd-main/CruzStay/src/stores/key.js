//use pinia to create a store for the key
import { defineStore } from 'pinia'
export const useSecret = defineStore({
    id: 'secret',
    state: () => ({
        secretKey: 'LLAVESECRETASIUU'
    }),
    actions: {
        getSecretKey() {
            return this.secretKey;
        }
    }
})