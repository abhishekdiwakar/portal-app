package com.linneaus.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  String title;
  String description;
  String link;
  String comments;
  String guid;
  String pubDate;
}
