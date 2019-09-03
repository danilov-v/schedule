import React from "react";
import PropTypes from "prop-types";
import { Table, Button } from "reactstrap";

export function PeriodsList({ periods, onPeriodEdit, onPeriodRemove }) {
  const renderRow = (period, index) => (
    <tr key={period.periodId}>
      <th scope="row">{index}</th>
      <td>{period.name}</td>
      <td>{period.startDate}</td>
      <td>{period.endDate}</td>
      <td>
        <Button onClick={onPeriodEdit.bind(null, period)} color="warning">
          Изменить
        </Button>
        <Button close onClick={onPeriodRemove.bind(null, period.periodId)} />
      </td>
    </tr>
  );
  return (
    <div className="event-type-list">
      <Table hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Название</th>
            <th>Начало</th>
            <th>Конец</th>
            <th></th>
          </tr>
        </thead>
        <tbody>{periods.map(renderRow)}</tbody>
      </Table>
    </div>
  );
}

PeriodsList.propTypes = {
  periods: PropTypes.arrayOf(
    PropTypes.shape({
      periodId: PropTypes.number,
      name: PropTypes.string,
      startDate: PropTypes.string,
      endDate: PropTypes.string,
    })
  ),
  onPeriodEdit: PropTypes.func.isRequired,
  onPeriodRemove: PropTypes.func.isRequired,
};

PeriodsList.defaultProps = {
  periods: [],
};