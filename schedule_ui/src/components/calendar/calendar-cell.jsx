import React from "react";
import PropTypes from "prop-types";
import classnames from "classnames";

import "./calendar.scss";

export function CalendarCell({ text, fluid, className }) {
  return (
    <div
      className={classnames(
        "calendar-cell d-flex align-items-center justify-content-center",
        className,
        {
          fluid,
        }
      )}
    >
      {text}
    </div>
  );
}

CalendarCell.propTypes = {
  text: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number,
    PropTypes.node,
  ]),
  className: PropTypes.string,
  fluid: PropTypes.bool,
};

CalendarCell.defaultProps = {
  text: null,
  className: "",
  fluid: false,
};
