//plugin for axios
// Este plugin permite que axios sea accedido desde cualquier componente de Vue
import axios from 'axios';

const axiosPlugin = {
    install(Vue) {
        Vue.prototype.$axios = axios;
    }
};

export default axiosPlugin;