import { TestBed } from '@angular/core/testing';

import { GestionModeloService } from './gestion-modelo.service';

describe('GestionModeloService', () => {
  let service: GestionModeloService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionModeloService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
