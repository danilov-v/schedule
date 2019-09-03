import { getDayWithoutMinutes } from "utils/date";

export const getLastGenUnits = units => {
  const getUnitLastChilds = unit =>
    unit.childUnit && unit.childUnit.length
      ? unit.childUnit.map(getUnitLastChilds)
      : [unit];

  return units.map(unit => getUnitLastChilds(unit).flat(4));
};

export const formatPeriods = periods =>
  periods.map(period => ({
    ...period,
    startDate: getDayWithoutMinutes(new Date(period.startDate)),
    endDate: getDayWithoutMinutes(new Date(period.endDate)),
  }));
