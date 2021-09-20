import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportparticipantsComponent } from './importparticipants.component';

describe('ImportparticipantsComponent', () => {
  let component: ImportparticipantsComponent;
  let fixture: ComponentFixture<ImportparticipantsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImportparticipantsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportparticipantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
