import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsandeventsComponent } from './newsandevents.component';

describe('NewsandeventsComponent', () => {
  let component: NewsandeventsComponent;
  let fixture: ComponentFixture<NewsandeventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsandeventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsandeventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
