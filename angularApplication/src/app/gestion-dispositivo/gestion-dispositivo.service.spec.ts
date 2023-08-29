import { TestBed } from '@angular/core/testing';

import { GestionDispositivoService } from './gestion-dispositivo.service';

describe('GestionDispositivoService', () => {
  let service: GestionDispositivoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionDispositivoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
