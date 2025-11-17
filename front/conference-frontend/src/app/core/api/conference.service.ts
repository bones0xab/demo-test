import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {

  // On utilise l'URL du Gateway + le nom du service
  private apiUrl = `${environment.apiUrl}/conference-service`;

  constructor(private http: HttpClient) { }

  public getAllConferences(): Observable<any[]> {
    // L'intercepteur va ajouter le token AUTOMATIQUEMENT
    return this.http.get<any[]>(`${this.apiUrl}/conferences`);
  }

}
