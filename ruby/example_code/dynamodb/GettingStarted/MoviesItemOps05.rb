# Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
# SPDX-License-Identifier: Apache-2.0

# This code example demonstrates how to update information about an
# existing item in an existing table in Amazon DynamoDB named 'Movies'.
# If an item with the specified attributes exists in the table,
# information about that item is updated. In this example, the item
# must have a 'year' attribute value of 2015 and a 'title' attribute value
# of 'The Big New Movie'. If so, and if the 'actors' value of the item's
# 'info' attribute contains more than three, then value of the first
# actor is removed. Only information about the updated attribute is
# returned, as specified by 'UPDATED_NEW'.

# snippet-start:[dynamodb.Ruby.CodeExample.MoviesItemOps05]
require 'aws-sdk-dynamodb'

def table_item_updated?(dynamodb_client, table_item)
  result = dynamodb_client.update_item(table_item)
  puts "Table item updated with the following attributes for 'info':"
  result.attributes['info'].each do |key, value|
    if key == 'rating'
      puts "#{key}: #{value.to_f}"
    else
      puts "#{key}: #{value}"
    end
  end
  true
rescue StandardError => e
  puts "Error updating item: #{e.message}"
  false
end

def run_me
  region = 'us-west-2'
  table_name = 'Movies'
  title = 'The Big New Movie'
  year = 2015

  # To use the downloadable version of Amazon DynamoDB,
  # uncomment the endpoint statement.
  Aws.config.update(
    # endpoint: 'http://localhost:8000',
    region: region
  )

  dynamodb_client = Aws::DynamoDB::Client.new

  table_item = {
    table_name: table_name,
    key: {
      year: year,
      title: title
    },
    update_expression: 'REMOVE info.actors[0]',
    condition_expression: 'size(info.actors) > :num',
    expression_attribute_values: {
      ':num': 3
    },
    return_values: 'UPDATED_NEW'
  }

  puts "Updating table '#{table_name}' with information about " \
    "'#{title} (#{year})'..."

  if table_item_updated?(dynamodb_client, table_item)
    puts 'Table updated.'
  else
    puts 'Table not updated.'
  end
end

run_me if $PROGRAM_NAME == __FILE__
# snippet-end:[dynamodb.Ruby.CodeExample.MoviesItemOps05]
