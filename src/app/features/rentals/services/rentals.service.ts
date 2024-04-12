import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/features/rentals/interfaces/rental.interface';
import { RentalResponse } from '../interfaces/api/rentalResponse.interface';
import { RentalsResponse } from '../interfaces/api/rentalsResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class RentalsService {
  private pathService = 'api/rentals';

  constructor(private httpClient: HttpClient) {}

  public all(): Observable<RentalsResponse> {
    console.log(
      'all:' +
        this.httpClient
          .get<RentalsResponse>(this.pathService)
          .forEach((rental) => {
            //const r:RentalResponse = rental.rentals;
            console.log('rental:' + rental.toString());
          })
    );
    return this.httpClient.get<RentalsResponse>(this.pathService);
  }

  public detail(id: string): Observable<Rental> {
    return this.httpClient.get<Rental>(`${this.pathService}/${id}`);
  }

  public create(form: FormData): Observable<RentalResponse> {
    return this.httpClient.post<RentalResponse>(this.pathService, form);
  }

  public update(id: string, form: FormData): Observable<RentalResponse> {
    return this.httpClient.put<RentalResponse>(
      `${this.pathService}/${id}`,
      form
    );
  }
}
