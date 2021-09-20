import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestscheduleComponent } from './testschedule.component';

describe('TestscheduleComponent', () => {
  let component: TestscheduleComponent;
  let fixture: ComponentFixture<TestscheduleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestscheduleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestscheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
