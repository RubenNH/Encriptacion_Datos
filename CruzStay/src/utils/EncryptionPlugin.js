import encryptionService from "./EncryptionService";

const encryptionPlugin = {
    install(Vue) {
        Vue.prototype.$encryptionService = encryptionService;
    }
};

export default encryptionPlugin;