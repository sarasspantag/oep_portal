import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewfacultyProfilelistComponent } from './viewfaculty-profilelist.component';

describe('ViewfacultyProfilelistComponent', () => {
  let component: ViewfacultyProfilelistComponent;
  let fixture: ComponentFixture<ViewfacultyProfilelistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewfacultyProfilelistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewfacultyProfilelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
