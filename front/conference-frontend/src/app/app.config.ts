import { ApplicationConfig, APP_INITIALIZER } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

import { routes } from './app.routes'; // Vos routes
import { KeycloakService } from './core/keycloak.service';
import { authInterceptor } from './core/auth.interceptor';

// Fonction pour initialiser Keycloak AVANT le reste
export function initializeKeycloak(keycloak: KeycloakService) {
  return () => keycloak.init();
}

export const appConfig: ApplicationConfig = {
  providers: [
    // 1. Fournir les routes
    provideRouter(routes),

    // 2. Fournir HttpClient ET enregistrer l'intercepteur (la nouvelle façon)
    provideHttpClient(
      withInterceptors([authInterceptor])
    ),

    // 3. Fournir le service Keycloak
    KeycloakService,

    // 4. Dire à Angular d'ATTENDRE que Keycloak soit initialisé
    //    avant de démarrer l'application (c'est crucial)
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      deps: [KeycloakService],
      multi: true
    }
  ]
};
