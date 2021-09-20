import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UmmComponent } from './umm.component';

describe('UmmComponent', () => {
  let component: UmmComponent;
  let fixture: ComponentFixture<UmmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UmmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UmmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
