import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { KeycloakService } from './keycloak.service';
import { environment } from '../../environments/environment';

// C'est la nouvelle façon (fonctionnelle) de créer un intercepteur
export const authInterceptor: HttpInterceptorFn = (req, next) => {

  // 1. On récupère le service Keycloak
  const keycloakService = inject(KeycloakService);
  const token = keycloakService.getToken();

  // 2. On vérifie si la requête va bien vers NOTRE API (pas vers Keycloak)
  if (token && req.url.startsWith(environment.apiUrl)) {

    // 3. On clone la requête et on attache le token
    const authReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    // 4. On envoie la requête sécurisée
    return next(authReq);
  }

  // 5. Sinon, on laisse passer (ex: l'appel à Keycloak lui-même)
  return next(req);
};
