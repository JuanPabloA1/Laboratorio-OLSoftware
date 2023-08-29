import { TestBed } from '@angular/core/testing';

import { GestionAreasService } from './gestion-areas.service';

describe('GestionAreasService', () => {
  let service: GestionAreasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GestionAreasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
