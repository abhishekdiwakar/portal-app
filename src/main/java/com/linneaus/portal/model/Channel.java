package com.linneaus.portal.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
  String title;
  String description;
  String link;
  String language;
  String creator;
  List<Item> items;
}