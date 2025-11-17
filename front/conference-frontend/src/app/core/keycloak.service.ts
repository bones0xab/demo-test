import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  private keycloak: Keycloak;

  constructor() {
    this.keycloak = new Keycloak(environment.keycloak);
  }

  // 1. Fonction d'initialisation (appelée avant le démarrage de l'app)
  init(): Promise<boolean> {
    return this.keycloak.init({
      onLoad: 'login-required' // Force l'utilisateur à se connecter
    });
  }

  // 2. Fonction pour que les autres parties de l'app récupèrent le token
  getToken(): string | undefined {
    return this.keycloak.token;
  }
}
