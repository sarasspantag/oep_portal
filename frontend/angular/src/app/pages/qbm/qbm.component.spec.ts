import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QbmComponent } from './qbm.component';

describe('QbmComponent', () => {
  let component: QbmComponent;
  let fixture: ComponentFixture<QbmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QbmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QbmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
