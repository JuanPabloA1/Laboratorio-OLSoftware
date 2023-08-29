import { TestBed } from '@angular/core/testing';

import { GestionRolService } from './gestion-rol.service';

describe('GestionRolService', () => {
  let service: GestionRolService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionRolService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
