import {AbstractPlotTypeModel} from './abstract.plot.type.model';
import {TypeEntityModel} from './typeEntity.model';

export class PlotModel extends AbstractPlotTypeModel {

  area;
  length;
  width;
  typeEntity: TypeEntityModel[];

}
