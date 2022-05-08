import {AbstractPlotTypeModel} from './abstract.plot.type.model';
import {PlotModel} from './plot.model';
import {TimeEntityModel} from './time.entity.model';

export class TypeEntityModel extends AbstractPlotTypeModel {

  type;
  timeEntityList: TimeEntityModel[];
  plotEntityList: PlotModel[];

  results;


}
