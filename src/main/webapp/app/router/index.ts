import { HomeVue } from '@/common/primary/home';
import { createRouter, createWebHashHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeVue,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;