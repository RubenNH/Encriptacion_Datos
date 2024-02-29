//plugin for axios
import axios from 'axios';

const axiosPlugin = {
    install(Vue) {
        Vue.prototype.$axios = axios;
    }
};

export default axiosPlugin;