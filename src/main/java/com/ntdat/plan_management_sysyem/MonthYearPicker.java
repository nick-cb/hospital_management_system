package com.ntdat.plan_management_sysyem;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MonthYearPicker extends ComboBox<YearMonth> {
    public static final EventType<Event> USER_SELECTED_EVENT = new EventType<>(Event.ANY, "USER_SELECTED_EVENT");

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
    private ListView<YearMonth> listView;
    private List<YearMonth> items;
    private int maxYear = 2040;
    private int maxMonth = 12;
    private int minYear = 2000;
    private int minMonth = 1;
    private Order order = Order.ASC;

    public static class UserSelectedEvent extends Event {
        public UserSelectedEvent() {
            super(null, null, USER_SELECTED_EVENT);
        }
    }

    public MonthYearPicker() {
        items = IntStream.rangeClosed(minYear, maxYear)
                .mapToObj(i -> (i == maxYear ? IntStream.rangeClosed(minMonth, maxMonth) : IntStream.rangeClosed(1, 12))
                        .mapToObj(j -> YearMonth.of(i, j))
                )
                .flatMap(i -> i)
                .collect(Collectors.toList());

        setItems(FXCollections.observableArrayList(items));

        setConverter(new StringConverter<YearMonth>() {
            @Override
            public String toString(YearMonth object) {
                if (object != null) {
                    return formatter.format(object);
                } else {
                    return "";
                }
            }

            @Override
            public YearMonth fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return YearMonth.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

        setCellFactory(comboBox -> {
            ListCell<YearMonth> cell = new ListCell<>() {
                @Override
                protected void updateItem(YearMonth item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                }
            };

            showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
                if (isNowShowing) {
                    listView = cell.getListView();
                }
            });

            return cell;
        });

        addEventFilter(ActionEvent.ACTION, event -> {
            if (isShowing()) {
                // If the ComboBox is showing, this is a user selection, so fire the custom event
                UserSelectedEvent userSelectedEvent = new UserSelectedEvent();
                this.fireEvent(userSelectedEvent);
                event.consume();
            }
        });
    }

    public void setValue(LocalDate date) {
        setValue(YearMonth.from(date));
    }

    public void setMax(YearMonth date) {
        maxYear = date.getYear();
        maxMonth = date.getMonthValue();
        items = IntStream.rangeClosed(minYear, maxYear)
                .mapToObj(i -> {

                    return (i == maxYear ? IntStream.rangeClosed(1, maxMonth) : IntStream.rangeClosed(1, 12))
                            .mapToObj(j -> YearMonth.of(i, j));
                })
                .flatMap(i -> i)
                .collect(Collectors.toList());
        if (order == Order.DESC) {
            items.sort(Comparator.reverseOrder());
        } else {
            items.sort(Comparator.naturalOrder());
        }
        setItems(FXCollections.observableArrayList(items));
    }

    public void setMin(YearMonth date) {
        minYear = date.getYear();
        minMonth = date.getMonthValue();
        items = IntStream.rangeClosed(minYear, maxYear)
                .mapToObj(i -> {
                    return (i == minYear ? IntStream.rangeClosed(minMonth, 12) : IntStream.rangeClosed(1, 12))
                            .mapToObj(j -> YearMonth.of(i, j));
                })
                .flatMap(i -> i)
                .collect(Collectors.toList());
        if (order == Order.DESC) {
            items.sort(Comparator.reverseOrder());
        } else {
            items.sort(Comparator.naturalOrder());
        }
        setItems(FXCollections.observableArrayList(items));
    }

    public void setOrder(Order order) {
        if (order == this.order) {
            return;
        }
        if (order == Order.DESC) {
            items.sort(Comparator.reverseOrder());
        } else {
            items.sort(Comparator.naturalOrder());
        }
        this.order = order;
        setItems(FXCollections.observableArrayList(items));
    }

    public void scrollTo() {
        if (listView != null) {
            listView.scrollTo(getValue());
        }
    }
}
