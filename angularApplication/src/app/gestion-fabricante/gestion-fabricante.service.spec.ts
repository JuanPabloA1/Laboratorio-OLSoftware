import { TestBed } from '@angular/core/testing';

import { GestionFabricanteService } from './gestion-fabricante.service';

describe('GestionFabricanteService', () => {
  let service: GestionFabricanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionFabricanteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
